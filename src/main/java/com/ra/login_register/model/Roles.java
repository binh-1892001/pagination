package com.ra.login_register.model;

import com.ra.login_register.constants.RoleName;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Roles {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	private RoleName roleName; // ROLE_ADMIN || ROLE_USER
}
