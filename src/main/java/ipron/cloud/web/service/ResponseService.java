package ipron.cloud.web.service;

import ipron.cloud.web.model.response.CommonResult;
import ipron.cloud.web.model.response.ListResult;
import ipron.cloud.web.model.response.MultiResult;
import ipron.cloud.web.model.response.SingleResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {
    public enum CommonResponse {
        SUCCESS(0, "성공"),
        FAIL(-1, "실패")
        ;

        int code;
        String msg;

        CommonResponse(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() { return code; }
        public String getMsg() { return msg; }
    }

    /**
     * 단일 결과 처리
     * @param data
     * @param <T>
     * @return
     */
    public <T> SingleResult<T> getSingleResult(T data) {
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        setSuccessResult(result);
        return result;
    }

    /**
     * 다중 결과 처리 (multi)
     * @param list
     * @param <T>
     * @return data
     */
    public <T> MultiResult<T> getMultiResult(List<T> list) {
        MultiResult<T> result = new MultiResult<>();
        result.setData(list);
        setSuccessResult(result);
        return result;
    }

    /**
     * 다중 결과 처리 (list)
     * @param list
     * @param <T>
     * @return list
     */
    public <T> ListResult<T> getListResult(List<T> list) {
        ListResult<T> result = new ListResult<>();
        result.setList(list);
        setSuccessResult(result);
        return result;
    }

    /**
     * api 요청 성공 데이터 세팅
     * @param result
     */
    private void setSuccessResult(CommonResult result) {
        result.setResult(Boolean.TRUE);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }

    /**
     * 성공 결과 처리
     * @return
     */
    public CommonResult getSuccessResult() {
        CommonResult result = new CommonResult();
        setSuccessResult(result);
        return result;
    }

    /**
     * 실패 결과 처리
     * @return
     */
    public CommonResult getFailResult() {
        CommonResult result = new CommonResult();
        result.setResult(Boolean.FALSE);
        result.setCode(CommonResponse.FAIL.getCode());
        result.setMsg(CommonResponse.FAIL.getMsg());
        return result;
    }

    /**
     * i18n 적용 실패 결과 처리
     * @param code
     * @param msg
     * @return
     */
    public CommonResult getFailResult(int code, String msg) {
        CommonResult result = new CommonResult();
        result.setResult(Boolean.FALSE);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
