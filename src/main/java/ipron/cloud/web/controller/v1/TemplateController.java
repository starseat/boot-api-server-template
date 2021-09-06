package ipron.cloud.web.controller.v1;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"Template"})
@RestController
@RequestMapping(value = "/template/v1")
@RequiredArgsConstructor
@Slf4j
public class TemplateController {
}
