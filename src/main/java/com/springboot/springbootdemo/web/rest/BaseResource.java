package com.springboot.springbootdemo.web.rest;


import com.springboot.springbootdemo.dto.base.BaseDTO;
import com.springboot.springbootdemo.dto.base.ResponseDTO;
import com.springboot.springbootdemo.enums.base.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseResource<T extends BaseDTO> {

    protected final Logger logger= LoggerFactory.getLogger(getClass());

    public <K> ResponseDTO<K> getResponseDTO(String code, String msg, K obj){
        ResponseDTO<K> responseDTO = new ResponseDTO(code, msg);
        responseDTO.setData(obj);
        return responseDTO;
    }
    public <K> ResponseDTO<K> success(){
        return new ResponseDTO(ResponseCode.CODE_200.getCode(), ResponseCode.CODE_200.getMsg());
    }
    public <K> ResponseDTO<K> success(K obj){
        ResponseDTO<K> responseDTO = new ResponseDTO(ResponseCode.CODE_200.getCode(), ResponseCode.CODE_200.getMsg());
        responseDTO.setData(obj);
        return responseDTO;
    }
    public <K> ResponseDTO<K> success(K obj,String msg){
        ResponseDTO<K> responseDTO = new ResponseDTO(ResponseCode.CODE_200.getCode(), msg);
        responseDTO.setData(obj);
        return responseDTO;
    }
    public <K> ResponseDTO<K> fail(){
        return new ResponseDTO(ResponseCode.CODE_400.getCode(), ResponseCode.CODE_400.getMsg());
    }
    public <K> ResponseDTO<K> fail(String msg){
        return new ResponseDTO(ResponseCode.CODE_400.getCode(),msg);
    }
}