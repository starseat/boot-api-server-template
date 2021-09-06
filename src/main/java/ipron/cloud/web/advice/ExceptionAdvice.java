package ipron.cloud.web.advice;

import ipron.cloud.web.advice.exception.NotFoundException;
import ipron.cloud.web.model.response.CommonResult;
import ipron.cloud.web.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    private final ResponseService responseService;
    private final MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // Exception 발생히 Http Error 500 으로 설정
    protected CommonResult defaultException(HttpServletRequest request, Exception e) {
        //return responseService.getFailResult();

        // i18n 적용
        return responseService.getFailResult(
                Integer.valueOf(getMessage("unKnown.code")),
                getMessage("unKnown.msg")
        );
    }

    @ExceptionHandler(NotFoundException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected CommonResult notFoundException(HttpServletRequest request, NotFoundException e) {
        //return responseService.getFailResult();

        // i18n 적용
        return responseService.getFailResult(
                Integer.valueOf(getMessage("notFound.code")),
                getMessage("notFound.msg")
        );
    }

    // ====================================================================================================
    // ====================================================================================================
    // ====================================================================================================

    /**
     * code 정보에 해당하는 메시지 조회
     * @param code
     * @return
     */
    private String getMessage(String code) {
        return getMessage(code, null);
    }

    /**
     * code 정보, 추가 argument로 현재 local에 맞는 메시지 조회
     * @param code
     * @param args
     * @return
     */
    private String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
