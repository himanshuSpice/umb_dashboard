package com.spice.service.creation.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class UpdateServiceRequest {
	@Pattern(regexp="([0-9]+)?",message="103")
	private String nodeId;
	@Pattern(regexp="([0-9]+)?",message="103")
	private String parentId;
	@Pattern(regexp="([0-9]+)?",message="103")
	private String status;
	@NotBlank(message="105")
	@NotNull(message="105")
	@Pattern(regexp="([0-9]+)?",message="103")
	private String serviceId;
	@Length(max=40,message="103")
	@Pattern(regexp="(([0-9]+)|([a-zA-Z]+)|(?=.*\\d)?[(a-zA-Z)*]?(?=.*[_]?).{3,50})?",message="103")
	private String serviceCode;
	private String requestText;
	private String responseText;
	private String regex;
	private String invalidResponseText;
	private String invalidMenuFlag;
	private String nodeType;
	@Pattern(regexp="([0-9]+)?",message="103")
	private String isHeader;
	@Pattern(regexp="([0-9]+)?",message="103")
	private String isFooter;
	private String isListElement;
	private String preference;
	private String menuFlag;
	private String dcs;
	private String applicationId;
	@Pattern(regexp="([0-9]+)?",message="103")
	private String variableName;
	private String variableValue;
	private String switchServiceId;
	private String switchServiceCode;
	private String circleId;
	private String operatorId;
	private String ifClause;
	private String ifValueClause;

}
