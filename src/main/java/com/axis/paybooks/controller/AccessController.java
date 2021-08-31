package com.axis.paybooks.controller;

import java.util.Arrays;
import java.util.Date;
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

import com.axis.paybooks.constant.PermissionURLConstants;
import com.axis.paybooks.jwt.JwtAuthTokenFilter;
import com.axis.paybooks.model.Resource;
import com.axis.paybooks.model.ResourcePerm;
import com.axis.paybooks.model.Role;
import com.axis.paybooks.model.RolePermission;
import com.axis.paybooks.model.User;
import com.axis.paybooks.model.User_Role;
import com.axis.paybooks.repository.UserRepository;
import com.axis.paybooks.request.CompleteDetails;
import com.axis.paybooks.request.EmailRequestDto;
import com.axis.paybooks.request.SignUpForm;
import com.axis.paybooks.response.ResponseMessage;
import com.axis.paybooks.service.AccessServiceImpl;

@RestController
@RequestMapping("/api/admin")
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

	
	
	
	@PostMapping("/register")
//	@PreAuthorize("hasPermission('" + PermissionURLConstants.HR_API_SERVICE + "','" + PermissionURLConstants.ADD + "')")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {

		if (userRepository.existsByEmailId(signUpRequest.getEmailId())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
					HttpStatus.BAD_REQUEST);
		}
		Date date = new Date();
		

		String crunchifyUUID = UUID.randomUUID().toString();

		User user = new User(signUpRequest.getName(),signUpRequest.getEmailId(),signUpRequest.getGender(), signUpRequest.getMobile(), encoder.encode(crunchifyUUID));

		email = new EmailRequestDto("Paybooks Login Credentials",signUpRequest.getEmailId(),
				"username : " + signUpRequest.getEmailId() + "   password : " + crunchifyUUID);
		System.out.println(signUpRequest.getEmailFrom()+"****************");
		userRepository.save(user);
		
		User details=userRepository.findUserByEmailId(signUpRequest.getEmailId());
		
		User_Role userRole = new User_Role(details.getId(),signUpRequest.getRole());
		accessService.setRole(userRole);
		
		return new ResponseEntity<>(new ResponseMessage("User registered successfully!",details.getId(),details.getName(),details.getEmailId(),details.getGender(),details.getMobile()), HttpStatus.OK);
	}

	
	
	
	
	@PostMapping("/email")
	public String sendEmail(@Valid @RequestBody EmailRequestDto emailRequestDto) {

		
		emailRequestDto.setEmail(email.getEmail());
		emailRequestDto.setBody(email.getBody());
		emailRequestDto.setSubject(email.getSubject());

		HttpHeaders headers = new HttpHeaders();
		
		headers.setBearerAuth(JwtAuthTokenFilter.jwt.toString());
		headers.setAccept(Arrays.asList(org.springframework.http.MediaType.APPLICATION_JSON));
		HttpEntity<EmailRequestDto> entity = new HttpEntity<EmailRequestDto>(emailRequestDto, headers);

		ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8082/api/access/email",
				HttpMethod.POST, entity, String.class);

		return responseEntity.getBody();
	}

	@PostMapping("/setRole")
	@PreAuthorize("hasPermission('" + PermissionURLConstants.HR_API_SERVICE + "','" + PermissionURLConstants.ADD + "')")
	public String setRoletoUser(@RequestBody User_Role userRole) {

		return accessService.setRole(userRole);
	}

	@PostMapping("/addResource")
	//@PreAuthorize("hasPermission('" + PermissionURLConstants.HR_API_SERVICE + "','" + PermissionURLConstants.ADD + "')")
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
	//@PreAuthorize("hasPermission('" + PermissionURLConstants.HR_API_SERVICE + "','" + PermissionURLConstants.ADD + "')")
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
//	@PreAuthorize("hasPermission('" + PermissionURLConstants.HR_API_SERVICE + "','" + PermissionURLConstants.VIEW
//			+ "')")
	public ResourcePerm getPermissionsByRoleName(@PathVariable String roleName) {

		Role role = getByRoleName(roleName);

		List<RolePermission> rolepermlist = accessService.getPermissionsByRoleId(role.getRoleID());

		ResourcePerm resourcePerm = new ResourcePerm();

		resourcePerm.setRoleName(roleName);
		resourcePerm.setPermissionList(rolepermlist);

		return resourcePerm;
	}

	@GetMapping("/getpermissions/{roleID}")
//	@PreAuthorize("hasPermission('" + PermissionURLConstants.HR_API_SERVICE + "','" + PermissionURLConstants.VIEW
//			+ "')")
	public List<RolePermission> getPermissionsByRoleId(@PathVariable int roleID) {

		return accessService.getPermissionsByRoleId(roleID);
	}
	
	
	@GetMapping("/getall")
//	@PreAuthorize("hasPermission('" + PermissionURLConstants.HR_API_SERVICE + "','" + PermissionURLConstants.VIEW
//			+ "')")
	public List<User> getAllUser(){
		return accessService.findAllUser();
	}
	
	
     @GetMapping("/getAllRoles")
//     @PreAuthorize("hasPermission('" + PermissionURLConstants.HR_API_SERVICE + "','" + PermissionURLConstants.VIEW
// 			+ "')")
     public List<Role> getAllRoles(){
    	 return accessService.getAllRoles();
     }
     
     @GetMapping("/getAllResources")
//     @PreAuthorize("hasPermission('" + PermissionURLConstants.HR_API_SERVICE + "','" + PermissionURLConstants.VIEW
// 			+ "')")
     public List<Resource> getAllResources(){
    	 return accessService.getAllResources();
     }
     
     @GetMapping("/getEmployee/{userId}")
     @PreAuthorize("hasPermission('" + PermissionURLConstants.HR_API_SERVICE + "','" + PermissionURLConstants.VIEW
  			+ "')")
     public CompleteDetails getEmp(@PathVariable("userId") String userId){
    	 return accessService.getEmployeeById(userId);
     }
     
     
}
