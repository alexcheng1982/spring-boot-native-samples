package io.vividcode.springnative.jpawebmvc.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

  @Autowired UserService userService;

  @Operation(
      summary = "Get user",
      operationId = "user.get",
      parameters = {
        @Parameter(
            name = "userId",
            description = "User ID",
            in = ParameterIn.PATH,
            example = "1",
            required = true)
      },
      responses = {
        @ApiResponse(
            description = "Successful operation",
            responseCode = "200",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = UserVO.class))),
        @ApiResponse(description = "Not found", responseCode = "404", content = @Content)
      })
  @Tag(name = "User", description = "User service")
  @GetMapping(path = "{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserVO> getUser(@PathVariable("userId") Long userId) {
    return this.userService
        .find(userId)
        .map(
            user ->
                new UserVO(
                    user.getId(),
                    user.getUsername(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getGender()))
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }
}
