1. 编写异常处理主业务
    ```java
    @Slf4j
    @ResponseBody  
    @ControllerAdvice
    public class GlobalExceptionHandler {
        @ExceptionHandler(BaseException.class)
        public BaseResponse baseExceptionHandler(BaseException ex){
            log.error("global base exception [{}] ", CommonConstants.EX_BASE_CODE, ex);
            return new BaseResponse(ex.getCode(), ex.getMessage()) ;
        }
        @ExceptionHandler(Exception.class)
        public BaseResponse otherExceptionHandler(Exception ex){
            log.error("global other exception [{}] ", CommonConstants.EX_OTHER_CODE, ex);
            return new BaseResponse(CommonConstants.EX_OTHER_CODE, ex.getMessage()) ;
        }
        @ExceptionHandler(TokenException.class)
        public BaseResponse tokenExceptionHandler(Exception ex){
            log.error("global token exception [{}] ", CommonConstants.EX_TOKEN_CODE, ex);
            return new BaseResponse(CommonConstants.EX_TOKEN_CODE, ex.getMessage()) ;
        }
        @ExceptionHandler(DuplicateKeyException.class)
        public BaseResponse duplicateKeyExceptionHandler(DuplicateKeyException ex){
            log.error("global token exception [{}] ", CommonConstants.EX_DUPLICATE_KEY_CODE, ex);
            return new BaseResponse(CommonConstants.EX_DUPLICATE_KEY_CODE, ex.getMessage()) ;
        }
    }
    ```
2. 编写项目业务异常类及常量类
    ```java
    @Data
    public class BaseException extends RuntimeException{
        private String code ;
        public BaseException(String msg){
            super(msg);
            this.code = CommonConstants.EX_BASE_CODE ;
        }
        public BaseException(String code, String msg){
            super(msg);
            this.code = code ;
        }
    }
    public class DuplicateKeyException extends BaseException {
        public DuplicateKeyException(String msg) {
            super(CommonConstants.EX_DUPLICATE_KEY_CODE,msg);
        }
    }
    public class TokenException extends BaseException {
        public TokenException(String msg) {
            super(CommonConstants.EX_TOKEN_CODE, msg);
        }
    }
    public interface CommonConstants {
        String EX_BASE_CODE = "000-00-00-001" ;
        // 其他异常
        String EX_OTHER_CODE = "000-00-00-002" ;
        // token exception
        String EX_TOKEN_CODE = "000-00-00-003" ;
        // duplicate key
        String EX_DUPLICATE_KEY_CODE = "000-00-00-004" ;
   }
    ```
3. 编写controller测试类
    ```java
    @RestController
    @RequestMapping("/exception")
    public class GlobalExceptionController {
        @GetMapping("/hello/{id}")
        public String hello(@PathVariable String id){
            if ("1".equals(id)){
                throw new BaseException("base exception 1") ;
            }else if ("2".equals(id)){
                throw new TokenException("token exception 2") ;
            }else if ("3".equals(id)){
                throw new DuplicateKeyException("duplicate key 3") ;
            }
            return "hello world";
        }
    }
    ```