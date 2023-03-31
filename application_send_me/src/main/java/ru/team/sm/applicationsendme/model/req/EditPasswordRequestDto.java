package ru.team.sm.applicationsendme.model.req;

import lombok.Data;

@Data
public class EditPasswordRequestDto {
    String currentPassword;
    String editPassword;
}
