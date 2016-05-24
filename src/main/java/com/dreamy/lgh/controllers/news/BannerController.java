package com.dreamy.lgh.controllers.news;

import com.dreamy.lgh.beans.InterfaceBean;
import com.dreamy.lgh.controllers.LghController;
import com.dreamy.lgh.domain.banner.Banner;
import com.dreamy.lgh.enums.ErrorCodeEnums;
import com.dreamy.lgh.service.iface.banner.BannerService;
import com.dreamy.utils.JsonUtils;
import com.dreamy.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/23
 * Time: 下午1:33
 */
@Controller
@RequestMapping("/banner")
public class BannerController extends LghController {

    @Autowired
    private BannerService bannerService;

    @RequestMapping("/add")
    public String add(ModelMap modelMap,
                      @RequestParam(value = "title", required = false) String title,
                      @RequestParam(value = "type", defaultValue = "0") Integer type,
                      @RequestParam(value = "href", required = false) String href,
                      @RequestParam(value = "imageUrl", required = false) String imageUrl) {
        if (StringUtils.isNotEmpty(title) && StringUtils.isNotEmpty(href) && StringUtils.isNotEmpty(imageUrl)) {

            Banner banner = new Banner();
            banner.title(title).href(href).imageUrl(imageUrl).type(type);
            bannerService.save(banner);

            return redirect("/banner/list?type=" + type + "&pageName=bannerlist" + type);
        }

        modelMap.put("title", title);
        modelMap.put("href", href);
        modelMap.put("imageUrl", imageUrl);
        modelMap.put("type", type);

        return "/banner/add";
    }

    @RequestMapping(value = {"/edit"}, method = RequestMethod.GET)
    public String edit(ModelMap modelMap, Integer id) {

        Banner banner = bannerService.getById(id);
        modelMap.put("banner", banner);

        return "/banner/edit";
    }

    @RequestMapping(value = {"/edit"}, method = RequestMethod.POST)
    public String editDo(@RequestParam("id") Integer id, @RequestParam("title") String title, @RequestParam("href") String href, @RequestParam("imageUrl") String imageUrl) {
        Banner banner = bannerService.getById(id);
        if (banner != null) {
            banner.title(title).href(href).imageUrl(imageUrl);
            bannerService.updateByRecord(banner);
            return redirect("/banner/list?type="+banner.getType()+"&pageName=bannerlist"+banner.getType());
        }

        return null;
    }

    @RequestMapping("/list")
    public String list(ModelMap modelMap, @RequestParam(value = "type", defaultValue = "0") Integer type) {
        List<Banner> bannerList = bannerService.getAllByOrderAndType("id desc", type);

        modelMap.put("bannerList", bannerList);
        return "/banner/list";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public void delete(HttpServletResponse response, Integer id) {
        InterfaceBean bean = new InterfaceBean().success();
        Integer res = bannerService.deleteById(id);
        if (res == 0) {
            bean.failure(ErrorCodeEnums.delete_failed);
        }
        interfaceReturn(response, JsonUtils.toString(bean), "");
    }


}
