package com.spice.service.creation.request;

import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class UpdateUserStatus {
	@Pattern(regexp="(0|1)?",message="103")
	private String userMngtPermission;
	@Pattern(regexp="(0|1)?",message="103")
	private String serviceMngtPermission;
	private String userName;
	@Pattern(regexp="(0|1|3|4)?",message="103")
	private String status;

}
