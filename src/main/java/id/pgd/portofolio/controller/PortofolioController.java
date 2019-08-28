package id.pgd.portofolio.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import id.pgd.portofolio.dto.request.PortofolioDto;
import id.pgd.portofolio.dto.response.ResponseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping(value="/portofolio")
public class PortofolioController {
    private static final Logger logger = LoggerFactory.getLogger(PortofolioController.class);

    @PostMapping(value = "/tabemas")
    public ResponseService inquiry(@RequestBody @Valid PortofolioDto req, BindingResult result) throws Exception {
        logger.info("incoming request /portofolio/tabemas nomor rekening : " + req.getNorek());
        ResponseService res = new ResponseService();
        try {
// 			List<TblTabungan> tab = repoTab.findByNorek(req.getNorek());
            HashMap<String, String> hMap = new HashMap<String, String>();
            hMap.put("norek", req.getNorek());
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            res.setData(gson.toJson(hMap));
            res.setResponseCode("00");
            res.setResponseDesc("Approved");
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            res.setResponseCode("14");
            res.setResponseDesc("Account not found!");
        }

        logger.info("Outgoing request /portofolio/tabemas nomor rekening : " + req.getNorek());
        return res;
    }
}

