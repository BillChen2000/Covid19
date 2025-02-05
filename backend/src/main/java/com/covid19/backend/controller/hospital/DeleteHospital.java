package com.covid19.backend.controller.hospital;

import com.covid19.backend.controller.BaseController;
import com.covid19.backend.model.Result;
import com.covid19.backend.model.Hospital;
import com.covid19.backend.service.hospital.DeleteHospitalService;
import com.covid19.backend.service.hospital.GetHospitalInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
@Api(tags = "医院控制器", value = "和医院有关的控制器")
public class DeleteHospital {
    @Autowired
    public DeleteHospitalService deleteHospitalService;
    @Autowired
    public GetHospitalInfoService getHospitalInfoService;

    @PostMapping("/hospital/deleteHospitalByID")
    @ApiOperation(value = "删除医院信息",notes = "可以删除医院的信息")
    @ApiImplicitParam(name = "hospital_id",value = "医院ID")
    public Result deleteHospitalByID(
            @RequestParam(value = "hospital_id") long hospital_id,
            HttpServletRequest request)
    {
        if(deleteHospitalService.checkCurrentUserInfo(request) == -1) return Result.error(Result.CODE_UNAUTHORIZED, "账号信息错误。");
        Hospital hospital = getHospitalInfoService.geHospitalInfoByID(hospital_id);
        if(hospital == null) return Result.error(2012,"不存在该医院");
        deleteHospitalService.deleteHospitalByID(hospital_id);
        return Result.ok();
    }
}
