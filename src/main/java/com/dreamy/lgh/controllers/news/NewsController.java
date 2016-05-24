package com.dreamy.lgh.controllers.news;

import com.dreamy.beans.Page;
import com.dreamy.lgh.beans.InterfaceBean;
import com.dreamy.lgh.controllers.LghController;
import com.dreamy.lgh.domain.news.News;
import com.dreamy.lgh.enums.ErrorCodeEnums;
import com.dreamy.lgh.service.iface.banner.BannerService;
import com.dreamy.lgh.service.iface.news.NewsService;
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

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/23
 * Time: 下午1:33
 */
@Controller
@RequestMapping("/news")
public class NewsController extends LghController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private BannerService bannerService;

    @Override
    public boolean checkLogin() {
        return false;
    }

    @RequestMapping("/add")
    public String add(ModelMap modelMap,
                      @RequestParam(value = "title", required = false) String title,
                      @RequestParam(value = "href", required = false) String href,
                      @RequestParam(value = "info", required = false) String info,
                      @RequestParam(value = "type", defaultValue = "0") Integer type,
                      @RequestParam(value = "imageUrl", required = false) String imageUrl) {

        if (StringUtils.isNotEmpty(title) && StringUtils.isNotEmpty(href) && StringUtils.isNotEmpty(imageUrl) && StringUtils.isNotEmpty(info)) {
            News news = new News();
            news.title(title).href(href).imageUrl(imageUrl).info(info).type(type);

            newsService.save(news);
            return redirect("/news/list?type="+type+"&pageName=newslist"+type);
        }

        modelMap.put("type",type);
        return "/news/add";
    }

    @RequestMapping(value = "/list/wx")
    public String listWx(ModelMap modelMap, Page page, @RequestParam(value = "type", defaultValue = "0") Integer type) {

        modelMap.put("newsList", newsService.getByPageAndOrderAndType(page, "id desc",type));
        modelMap.put("bannerList", bannerService.getAllByOrderAndType("id desc", type));

        return "/news/list";
    }

    @RequestMapping(value = "/list")
    public String list(ModelMap modelMap, Page page,@RequestParam(value = "type", defaultValue = "0") Integer type) {

        modelMap.put("newsList", newsService.getByPageAndOrderAndType(page, "id desc",type));
        return "/news/admin_list";
    }

    @RequestMapping("/edit")
    public String edit(ModelMap modelMap, Integer id) {
        News news = newsService.getById(id);

        modelMap.put("news", news);
        return "/news/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editPost(@RequestParam("id") Integer id,
                           @RequestParam("title") String title,
                           @RequestParam("href") String href,
                           @RequestParam("info") String info,
                           @RequestParam("imageUrl") String imageUrl) {
        News news = newsService.getById(id);

        if (news != null) {
            news.title(title).href(href).info(info).imageUrl(imageUrl);
            newsService.updateByRecord(news);
            return redirect("/news/list?type="+news.getType()+"&pageName=newslist="+news.getType());
        }

        return "/news/edit";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public void delete(HttpServletResponse response, Integer id) {
        InterfaceBean bean = new InterfaceBean().success();
        Integer res = newsService.deleteById(id);
        if (res == 0) {
            bean.failure(ErrorCodeEnums.delete_failed);
        }
        interfaceReturn(response, JsonUtils.toString(bean), "");
    }


}
