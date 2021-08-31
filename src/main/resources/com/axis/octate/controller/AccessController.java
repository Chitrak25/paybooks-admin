package com.axis.octate.controller;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.axis.octate.constant.PermissionURLConstants;
import com.axis.octate.jwt.JwtAuthTokenFilter;
import com.axis.octate.model.Resource;
import com.axis.octate.model.ResourcePerm;
import com.axis.octate.model.Role;
import com.axis.octate.model.RolePermission;
import com.axis.octate.model.User;
import com.axis.octate.model.User_Role;
import com.axis.octate.repository.UserRepository;
import com.axis.octate.request.EmailRequestDto;
import com.axis.octate.request.SignUpForm;
import com.axis.octate.response.ResponseMessage;
import com.axis.octate.service.AccessServiceImpl;

@RestController
@RequestMapping("/api/access")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AccessController {

	@Autowired
	private AccessServiceImpl accessService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	RestTemplate restTemplate;

	EmailRequestDto email = null;

	@PostMapping("/hr/register")
	@PreAuthorize("hasPermission('" + PermissionURLConstants.HR_API_SERVICE + "','" + PermissionURLConstants.ADD + "')")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {

		if (userRepository.existsByEmailId(signUpRequest.getEmailId())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
					HttpStatus.BAD_REQUEST);
		}

		String crunchifyUUID = UUID.randomUUID().toString();

		User user = new User(signUpRequest.getEmailId(), encoder.encode(crunchifyUUID));

		email = new EmailRequestDto(signUpRequest.getEmailId(),
				"username : " + signUpRequest.getEmailId() + "   password : " + crunchifyUUID);
		System.out.println(signUpRequest.getEmailFrom()+"************************************************");
		userRepository.save(user);

		return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
	}

	@PostMapping("/hr/email")
	@PreAuthorize("hasPermission('" + PermissionURLConstants.HR_API_SERVICE + "','" + PermissionURLConstants.ADD + "')")
	public String sendEmail(@Valid @RequestBody EmailRequestDto emailRequestDto) {

		
		emailRequestDto.setEmail(email.getEmail());
		emailRequestDto.setBody(email.getBody());

		System.out.println(emailRequestDto.getFrom());
		System.out.println(emailRequestDto.getEmail());
		System.out.println(emailRequestDto.getBody());
		
		HttpHeaders headers = new HttpHeaders();
		

		headers.setBearerAuth(JwtAuthTokenFilter.jwt.toString());
		headers.setAccept(Arrays.asList(org.springframework.http.MediaType.APPLICATION_JSON));
		HttpEntity<EmailRequestDto> entity = new HttpEntity<EmailRequestDto>(emailRequestDto, headers);

		ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8082/api/access/hr/email",
				HttpMethod.POST, entity, String.class);

		return responseEntity.getBody();
	}

	@PostMapping("/setRole")
	@PreAuthorize("hasPermission('" + PermissionURLConstants.HR_API_SERVICE + "','" + PermissionURLConstants.ADD + "')")
	public String setRoletoUser(@RequestBody User_Role userRole) {

		return accessService.setRole(userRole);
	}

	@PostMapping("/addResource")
	@PreAuthorize("hasPermission('" + PermissionURLConstants.HR_API_SERVICE + "','" + PermissionURLConstants.ADD + "')")
	public Resource addResource(@RequestBody Resource resource) {

		return accessService.addResource(resource);
	}

	@PostMapping("/grantPerm")
	@PreAuthorize("hasPermission('" + PermissionURLConstants.HR_API_SERVICE + "','" + PermissionURLConstants.ADD + "')")
	// @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN')")
	public RolePermission grantPermissions(@RequestBody RolePermission rolePermission) {

		return accessService.grantPermissions(rolePermission);
	}

	@PostMapping("/addRole")
	@PreAuthorize("hasPermission('" + PermissionURLConstants.HR_API_SERVICE + "','" + PermissionURLConstants.ADD + "')")
	public Role addRole(@RequestBody Role role) {

		return accessService.addRole(role);

	}

	@GetMapping("/getroleByRoleId/{roleID}")
	@PreAuthorize("hasPermission('" + PermissionURLConstants.HR_API_SERVICE + "','" + PermissionURLConstants.VIEW
			+ "')")
	public Role getRoleByRoleID(@PathVariable int roleID) {

		return accessService.getRoleByRoleID(roleID);
	}

	@GetMapping("/getByRoleName/{roleName}")
	@PreAuthorize("hasPermission('" + PermissionURLConstants.HR_API_SERVICE + "','" + PermissionURLConstants.VIEW
			+ "')")
	public Role getByRoleName(@PathVariable String roleName) {

		return accessService.getByRoleName(roleName);
	}

	@PostMapping("/createRolewithPerm")
	@PreAuthorize("hasPermission('" + PermissionURLConstants.HR_API_SERVICE + "','" + PermissionURLConstants.ADD + "')")
	public String createRoleWithPermissions(@RequestBody ResourcePerm resourcePerm) {

		System.out.println("inside ");
		Role role = new Role();

		role.setRoleName(resourcePerm.getRoleName());

		Role roledata = accessService.addRole(role);

		System.out.println(roledata.getRoleID());
		System.out.println(resourcePerm.getPermissionList());

		for (RolePermission p : resourcePerm.getPermissionList()) {

			p.setRoleId(roledata.getRoleID());
		}

		System.out.println(resourcePerm.getPermissionList());
		for (RolePermission p : resourcePerm.getPermissionList()) {
			accessService.grantPermissions(p);
		}
		return "successFully created";

	}

	@PutMapping("/updatepermissionsByRoleID")
	@PreAuthorize("hasPermission('" + PermissionURLConstants.HR_API_SERVICE + "','" + PermissionURLConstants.EDIT
			+ "')")
	public String updatepermissionsByRoleIDAndResourceId(@RequestBody ResourcePerm resourcePerm) {

		System.out.println("inside update ");

		return accessService.updatepermissionsByRoleIDAndResourceId(resourcePerm);

	}

	@GetMapping("/getPermissionsByRoleName/{roleName}")
	@PreAuthorize("hasPermission('" + PermissionURLConstants.HR_API_SERVICE + "','" + PermissionURLConstants.VIEW
			+ "')")
	public ResourcePerm getPermissionsByRoleName(@PathVariable String roleName) {

		Role role = getByRoleName(roleName);

		List<RolePermission> rolepermlist = accessService.getPermissionsByRoleId(role.getRoleID());

		ResourcePerm resourcePerm = new ResourcePerm();

		resourcePerm.setRoleName(roleName);
		resourcePerm.setPermissionList(rolepermlist);

		return resourcePerm;
	}

	@GetMapping("/getpermissions/{roleID}")
	@PreAuthorize("hasPermission('" + PermissionURLConstants.HR_API_SERVICE + "','" + PermissionURLConstants.VIEW
			+ "')")
	public List<RolePermission> getPermissionsByRoleId(@PathVariable int roleID) {

		return accessService.getPermissionsByRoleId(roleID);
	}
	
	
	@GetMapping("/getall")
	@PreAuthorize("hasPermission('" + PermissionURLConstants.HR_API_SERVICE + "','" + PermissionURLConstants.VIEW
			+ "')")
	public List<User> getAllUser(){
		return accessService.findAllUser();
	}
	
	

}
