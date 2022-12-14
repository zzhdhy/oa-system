package com.zzh.oa_system.controller.mail;

import com.github.pagehelper.util.StringUtil;
import com.zzh.oa_system.common.formValid.BindingResultVOUtil;
import com.zzh.oa_system.common.formValid.MapToList;
import com.zzh.oa_system.common.formValid.ResultEnum;
import com.zzh.oa_system.common.formValid.ResultVO;
import com.zzh.oa_system.model.dao.maildao.InMailDao;
import com.zzh.oa_system.model.dao.maildao.MailnumberDao;
import com.zzh.oa_system.model.dao.maildao.MailreciverDao;
import com.zzh.oa_system.model.dao.notedao.AttachmentDao;
import com.zzh.oa_system.model.dao.roledao.RoleDao;
import com.zzh.oa_system.model.dao.system.StatusDao;
import com.zzh.oa_system.model.dao.system.TypeDao;
import com.zzh.oa_system.model.dao.user.DeptDao;
import com.zzh.oa_system.model.dao.user.PositionDao;
import com.zzh.oa_system.model.dao.user.UserDao;
import com.zzh.oa_system.model.entity.mail.Inmaillist;
import com.zzh.oa_system.model.entity.mail.Mailnumber;
import com.zzh.oa_system.model.entity.mail.Mailreciver;
import com.zzh.oa_system.model.entity.mail.Pagemail;
import com.zzh.oa_system.model.entity.note.Attachment;
import com.zzh.oa_system.model.entity.system.SystemStatusList;
import com.zzh.oa_system.model.entity.system.SystemTypeList;
import com.zzh.oa_system.model.entity.user.Dept;
import com.zzh.oa_system.model.entity.user.Position;
import com.zzh.oa_system.model.entity.user.User;
import com.zzh.oa_system.service.mail.MailService;
import com.zzh.oa_system.service.process.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

/**
 * projectName: oa_system
 *
 * @author: ?????????
 * time: 2021/4/29 19:44
 * description:
 */
@Controller
@RequestMapping("/")
public class MailController {


    @Autowired
    private MailnumberDao mndao;

    @Autowired
    private StatusDao sdao;
    @Autowired
    private TypeDao tydao;
    @Autowired
    private UserDao udao;
    @Autowired
    private DeptDao ddao;
    @Autowired
    private RoleDao rdao;
    @Autowired
    private PositionDao pdao;
    @Autowired
    private InMailDao imdao;
    @Autowired
    private MailreciverDao mrdao;
    @Autowired
    private AttachmentDao AttDao;
    @Autowired
    private MailService mservice;
    @Autowired
    private ProcessService proservice;

    /**
     * ??????????????????
     *
     * @return
     */
    @RequestMapping("mail")
    public String index(@SessionAttribute("userId") Long userId, Model model,
                        @RequestParam(value = "page", defaultValue = "0") int page,
                        @RequestParam(value = "size", defaultValue = "10") int size) {
        //????????????
        User user = udao.findOne(userId);
        //??????????????????
        List<Mailreciver> noreadlist = mrdao.findByReadAndDelAndReciverId(false, false, user);
        //?????????????????????????????????????????????
        List<Inmaillist> nopushlist = imdao.findByPushAndDelAndMailUserid(false, false, user);
        //??????????????????
        List<Inmaillist> pushlist = imdao.findByPushAndDelAndMailUserid(true, false, user);
        //????????????????????????????????????
        List<Mailreciver> rubbish = mrdao.findByDelAndReciverId(true, user);
        //???????????????
        Page<Pagemail> pagelist = mservice.recive(page, size, user, null, "?????????");
        List<Map<String, Object>> maillist = mservice.mail(pagelist);

        model.addAttribute("page", pagelist);
        model.addAttribute("maillist", maillist);
        model.addAttribute("url", "mailtitle");
        model.addAttribute("noread", noreadlist.size());
        model.addAttribute("nopush", nopushlist.size());
        model.addAttribute("push", pushlist.size());
        model.addAttribute("rubbish", rubbish.size());
        model.addAttribute("mess", "?????????");
        model.addAttribute("sort", "&title=?????????");
        return "mail/mail";
    }

    /**
     * ????????????
     * @param req
     * @param userId
     * @param model
     */
    @RequestMapping("alldelete")
    public String delete(HttpServletRequest req, @SessionAttribute("userId") Long userId, Model model,
                         @RequestParam(value = "page", defaultValue = "0") int page,
                         @RequestParam(value = "size", defaultValue = "10") int size) {
        //????????????
        User user = udao.findOne(userId);
        String title = req.getParameter("title");
        Page<Pagemail> pagelist = null;
        Page<Inmaillist> pagemail = null;
        List<Map<String, Object>> maillist = null;
        //????????????id
        String ids = req.getParameter("ids");
        if (("?????????").equals(title)) {

            StringTokenizer st = new StringTokenizer(ids, ",");
            while (st.hasMoreElements()) {
                //??????????????????????????????????????????
                Mailreciver mailr = mrdao.findbyReciverIdAndmailId(user, Long.parseLong(st.nextToken()));
                if (!Objects.isNull(mailr)) {
                    //????????????????????????1
                    mailr.setDel(true);
                    mrdao.save(mailr);
                } else {
                    return "redirect:/notlimit";
                }
            }
            //???????????????
            pagelist = mservice.recive(page, size, user, null, title);
            maillist = mservice.mail(pagelist);
        } else if (("?????????").equals(title)) {
            StringTokenizer st = new StringTokenizer(ids, ",");
            while (st.hasMoreElements()) {
                //???????????????
                Inmaillist inmail = imdao.findByMailUseridAndMailId(user, Long.parseLong(st.nextToken()));
                if (!Objects.isNull(inmail)) {
                    //????????????????????????1
                    inmail.setDel(true);
                    imdao.save(inmail);
                } else {
                    return "redirect:/notlimit";
                }
            }
            pagemail = mservice.inmail(page, size, user, null, title);
            maillist = mservice.maillist(pagemail);
        } else if (("?????????").equals(title)) {
            StringTokenizer st = new StringTokenizer(ids, ",");
            while (st.hasMoreElements()) {
                //???????????????
                Inmaillist inmail = imdao.findByMailUseridAndMailId(user, Long.parseLong(st.nextToken()));
                if (!Objects.isNull(inmail)) {
                    imdao.delete(inmail);
                } else {
                    return "redirect:/notlimit";
                }
            }
            pagemail = mservice.inmail(page, size, user, null, title);
            maillist = mservice.maillist(pagemail);
        } else {

            //?????????
            StringTokenizer st = new StringTokenizer(ids, ",");
            while (st.hasMoreElements()) {
                Long mailid = Long.parseLong(st.nextToken());
                //????????????????????????????????????del??????
                List<Boolean> dellist = mrdao.findbyMailId(mailid);

                //????????????????????????????????????????????????del?????????false???
                if (dellist.contains(false)) {
                    Mailreciver mailr = mrdao.findbyReciverIdAndmailId(user, mailid);
                    if (!Objects.isNull(mailr)) {
                        mrdao.delete(mailr);
                    } else {
                        return "redirect:/notlimit";
                    }
                } else {
                    Inmaillist imail = imdao.findOne(mailid);
                    //?????????????????????del????????????true
                    if (imail.getDel().equals(true)) {
                        List<Mailreciver> mreciver = mrdao.findByMailId(mailid);
                        //??????????????????????????????????????????????????????
                        for (Mailreciver mailreciver : mreciver) {
                            mrdao.delete(mailreciver);
                        }
                        imdao.delete(imail);
                    } else {
                        //???????????????del?????????false???????????????????????????
                        Mailreciver mailr = mrdao.findbyReciverIdAndmailId(user, mailid);
                        if (!Objects.isNull(mailr)) {
                            mrdao.delete(mailr);
                        } else {
                            return "redirect:/notlimit";
                        }
                    }
                }
            }
            pagelist = mservice.recive(page, size, user, null, title);
            maillist = mservice.mail(pagelist);
        }

        if (!Objects.isNull(pagelist)) {
            model.addAttribute("page", pagelist);
        } else {
            model.addAttribute("page", pagemail);
        }
        model.addAttribute("maillist", maillist);
        model.addAttribute("url", "mailtitle");
        model.addAttribute("mess", title);
        return "mail/mailbody";

    }

    /**
     * ????????????
     */
    @RequestMapping("watch")
    public String watch(@SessionAttribute("userId") Long userId, Model model, HttpServletRequest req,
                        @RequestParam(value = "page", defaultValue = "0") int page,
                        @RequestParam(value = "size", defaultValue = "10") int size) {
        User user = udao.findOne(userId);
        String title = req.getParameter("title");
        String ids = req.getParameter("ids");
        Page<Pagemail> pagelist = null;
        List<Map<String, Object>> maillist = null;

        if (("?????????").equals(title)) {
            StringTokenizer st = new StringTokenizer(ids, ",");
            while (st.hasMoreElements()) {
                //??????????????????????????????????????????
                Mailreciver mailr = mrdao.findbyReciverIdAndmailId(user, Long.parseLong(st.nextToken()));
                if (mailr.getRead().equals(false)) {

                    mailr.setRead(true);
                } else {

                    mailr.setRead(false);
                }

                mrdao.save(mailr);
            }
            //???????????????
            pagelist = mservice.recive(page, size, user, null, title);

        } else {
            //?????????
            StringTokenizer st = new StringTokenizer(ids, ",");
            while (st.hasMoreElements()) {
                //??????????????????????????????????????????
                Mailreciver mailr = mrdao.findbyReciverIdAndmailId(user, Long.parseLong(st.nextToken()));
                if (mailr.getRead().equals(false)) {
                    mailr.setRead(true);
                } else {
                    mailr.setRead(false);
                }
                mrdao.save(mailr);
            }
            //???????????????
            pagelist = mservice.recive(page, size, user, null, title);
        }
        maillist = mservice.mail(pagelist);

        model.addAttribute("page", pagelist);
        model.addAttribute("maillist", maillist);
        model.addAttribute("url", "mailtitle");
        model.addAttribute("mess", title);
        return "mail/mailbody";
    }

    /**
     * ????????????
     */
    @RequestMapping("star")
    public String star(@SessionAttribute("userId") Long userId, Model model, HttpServletRequest req,
                       @RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "size", defaultValue = "10") int size) {
        User user = udao.findOne(userId);
        String title = req.getParameter("title");
        String ids = req.getParameter("ids");
        Page<Pagemail> pagelist = null;
        Page<Inmaillist> pagemail = null;
        List<Map<String, Object>> maillist = null;

        if (("?????????").equals(title)) {
            StringTokenizer st = new StringTokenizer(ids, ",");
            while (st.hasMoreElements()) {

                //??????????????????????????????????????????
                Mailreciver mailr = mrdao.findbyReciverIdAndmailId(user, Long.parseLong(st.nextToken()));
                if (mailr.getStar().equals(false)) {
                    mailr.setStar(true);
                } else {
                    mailr.setStar(false);
                }
                mrdao.save(mailr);
            }
            //???????????????
            pagelist = mservice.recive(page, size, user, null, title);
            maillist = mservice.mail(pagelist);
        } else if (("?????????").equals(title)) {
            StringTokenizer st = new StringTokenizer(ids, ",");
            while (st.hasMoreElements()) {
                //???????????????
                Inmaillist inmail = imdao.findByMailUseridAndMailId(user, Long.parseLong(st.nextToken()));
                if (inmail.getStar().equals(false)) {
                    inmail.setStar(true);
                } else {
                    inmail.setStar(false);
                }
                imdao.save(inmail);
            }
            pagemail = mservice.inmail(page, size, user, null, title);
            maillist = mservice.maillist(pagemail);
        } else if (("?????????").equals(title)) {
            StringTokenizer st = new StringTokenizer(ids, ",");
            while (st.hasMoreElements()) {
                //???????????????
                Inmaillist inmail = imdao.findByMailUseridAndMailId(user, Long.parseLong(st.nextToken()));
                if (inmail.getStar().equals(false)) {
                    inmail.setStar(true);
                } else {
                    inmail.setStar(false);
                }
                imdao.save(inmail);
            }
            pagemail = mservice.inmail(page, size, user, null, title);
            maillist = mservice.maillist(pagemail);
        } else {
            //?????????
            StringTokenizer st = new StringTokenizer(ids, ",");
            while (st.hasMoreElements()) {
                //??????????????????????????????????????????
                Mailreciver mailr = mrdao.findbyReciverIdAndmailId(user, Long.parseLong(st.nextToken()));
                if (mailr.getStar().equals(false)) {
                    mailr.setStar(true);
                } else {
                    mailr.setStar(false);
                }
                mrdao.save(mailr);
            }
            //???????????????
            pagelist = mservice.recive(page, size, user, null, title);
            maillist = mservice.mail(pagelist);
        }

        if (!Objects.isNull(pagelist)) {
            model.addAttribute("page", pagelist);
        } else {
            model.addAttribute("page", pagemail);
        }
        model.addAttribute("maillist", maillist);
        model.addAttribute("url", "mailtitle");
        model.addAttribute("mess", title);
        return "mail/mailbody";
    }

    /**
     * ??????????????????
     */
    @RequestMapping("mailtitle")
    public String serch(@SessionAttribute("userId") Long userId, Model model, HttpServletRequest req,
                        @RequestParam(value = "page", defaultValue = "0") int page,
                        @RequestParam(value = "size", defaultValue = "10") int size) {
        User user = udao.findOne(userId);
        String title = req.getParameter("title");
        String val = null;
        Page<Pagemail> pagelist = null;
        Page<Inmaillist> pagemail = null;
        List<Map<String, Object>> maillist = null;

        if (!StringUtil.isEmpty(req.getParameter("val"))) {
            val = req.getParameter("val");
        }
        if (("?????????").equals(title)) {
            pagelist = mservice.recive(page, size, user, val, title);
            maillist = mservice.mail(pagelist);

        } else if (("?????????").equals(title)) {

            pagemail = mservice.inmail(page, size, user, val, title);
            maillist = mservice.maillist(pagemail);
        } else if (("?????????").equals(title)) {

            pagemail = mservice.inmail(page, size, user, val, title);
            maillist = mservice.maillist(pagemail);
        } else {
            //?????????
            pagelist = mservice.recive(page, size, user, val, title);
            maillist = mservice.mail(pagelist);
        }

        if (!Objects.isNull(pagelist)) {
            model.addAttribute("page", pagelist);
        } else {
            model.addAttribute("page", pagemail);
        }
        if (val != null) {
            model.addAttribute("sort", "&title=" + title + "&val=" + val);
        } else {
            model.addAttribute("sort", "&title=" + title);
        }
        model.addAttribute("maillist", maillist);
        model.addAttribute("url", "mailtitle");
        model.addAttribute("mess", title);
        return "mail/mailbody";
    }

    /**
     * ????????????
     */
    @RequestMapping("accountmanage")
    public String account(@SessionAttribute("userId") Long userId, Model model,
                          @RequestParam(value = "page", defaultValue = "0") int page,
                          @RequestParam(value = "size", defaultValue = "10") int size) {
        // ????????????????????????id???????????????
        User tu = udao.findOne(userId);

        Page<Mailnumber> pagelist = mservice.index(page, size, tu, null, model);
        List<Map<String, Object>> list = mservice.up(pagelist);

        model.addAttribute("account", list);
        model.addAttribute("page", pagelist);
        model.addAttribute("url", "mailpaixu");
        return "mail/mailmanage";
    }

    /**
     * ??????????????????
     * ?????????
     */
    @RequestMapping("mailpaixu")
    public String paixu(HttpServletRequest request, @SessionAttribute("userId") Long userId, Model model,
                        @RequestParam(value = "page", defaultValue = "0") int page,
                        @RequestParam(value = "size", defaultValue = "10") int size) {
        // ???????????????id?????????
        User tu = udao.findOne(userId);
        //?????????????????????
        String val = null;
        if (!StringUtil.isEmpty(request.getParameter("val"))) {

            val = request.getParameter("val");
        }
        Page<Mailnumber> pagelist = mservice.index(page, size, tu, val, model);
        List<Map<String, Object>> list = mservice.up(pagelist);
        model.addAttribute("account", list);
        model.addAttribute("page", pagelist);
        model.addAttribute("url", "mailpaixu");

        return "mail/mailtable";
    }

    /**
     * ????????????
     * ????????????
     */
    @RequestMapping("addaccount")
    public String add(@SessionAttribute("userId") Long userId, Model model, HttpServletRequest req) {
        // ????????????id?????????
        User tu = udao.findOne(userId);

        Mailnumber mailn = null;
        if (StringUtil.isEmpty(req.getParameter("id"))) {
            List<SystemTypeList> typelist = tydao.findByTypeModel("aoa_mailnumber");
            List<SystemStatusList> statuslist = sdao.findByStatusModel("aoa_mailnumber");
            model.addAttribute("typelist", typelist);
            model.addAttribute("statuslist", statuslist);

            if (!StringUtil.isEmpty((String) req.getAttribute("errormess"))) {
                mailn = (Mailnumber) req.getAttribute("mail");
                req.setAttribute("errormess", req.getAttribute("errormess"));
                model.addAttribute("mails", mailn);
                model.addAttribute("type", tydao.findname(mailn.getMailType()));
                model.addAttribute("status", sdao.findname(mailn.getStatus()));

            } else if (!StringUtil.isEmpty((String) req.getAttribute("success"))) {
                mailn = (Mailnumber) req.getAttribute("mail");
                req.setAttribute("success", "fds");
                model.addAttribute("mails", mailn);
                model.addAttribute("type", tydao.findname(mailn.getMailType()));
                model.addAttribute("status", sdao.findname(mailn.getStatus()));
            }
        } else {

            Long id = Long.parseLong(req.getParameter("id"));
            Mailnumber mailnum = mndao.findOne(id);
            model.addAttribute("type", tydao.findname(mailnum.getMailType()));
            model.addAttribute("status", sdao.findname(mailnum.getStatus()));
            model.addAttribute("mails", mailnum);


        }
        model.addAttribute("username", tu.getUserName());
        return "mail/addaccounts";
    }

    /**
     * ???????????????
     */
    @RequestMapping("saveaccount")
    public String save(HttpServletRequest request, @Valid Mailnumber mail, BindingResult br, @SessionAttribute("userId") Long userId) {
        User tu = udao.findOne(userId);
        request.setAttribute("mail", mail);
        ResultVO res = BindingResultVOUtil.hasErrors(br);
        if (!ResultEnum.SUCCESS.getCode().equals(res.getCode())) {
            List<Object> list = new MapToList<>().mapToList(res.getData());
            request.setAttribute("errormess", list.get(0).toString());

            System.out.println("list???????????????????????????" + mail);
            System.out.println("list????????????:" + list);
            System.out.println("list???????????????:" + list.get(0));
            System.out.println("?????????????????????????????????" + list.get(0).toString());

        } else {
            if (Objects.isNull(mail.getMailNumberId())) {
                mail.setMailUserId(tu);
                mail.setMailCreateTime(new Date());
                mndao.save(mail);
            } else {
                Mailnumber mails = mndao.findOne(mail.getMailNumberId());
                mails.setMailType(mail.getMailType());
                mails.setStatus(mail.getStatus());
                mails.setMailDes(mail.getMailDes());
                mails.setMailAccount(mail.getMailAccount());
                mails.setPassword(mail.getPassword());
                mails.setMailUserName(mail.getMailUserName());
                mndao.save(mails);
            }
            request.setAttribute("success", "???????????????");

        }

        return "forward:/addaccount";
    }

    /**
     * ????????????
     */
    @RequestMapping("dele")
    public String edit(HttpServletRequest request, @SessionAttribute("userId") Long userId) {
        //????????????id
        Long accountid = Long.parseLong(request.getParameter("id"));
        Mailnumber mail = mndao.findOne(accountid);
        if (mail.getMailUserId().getUserId().equals(userId)) {
            mservice.dele(accountid);
        } else {
            return "redirect:/notlimit";
        }
        System.out.println("ffffffff");
        return "redirect:/accountmanage";
    }

    /**
     * ??????
     */
    @RequestMapping("wmail")
    public String index2(Model model, @SessionAttribute("userId") Long userId, HttpServletRequest request,
                         @RequestParam(value = "page", defaultValue = "0") int page,
                         @RequestParam(value = "size", defaultValue = "10") int size) {

        User mu = udao.findOne(userId);
        //?????????????????????id
        String id = null;
        if (!StringUtil.isEmpty(request.getParameter("id"))) {
            id = request.getParameter("id");
        }
        //?????????????????????
        String huifu = null;

        if (!StringUtil.isEmpty(id)) {
            Long lid = Long.parseLong(id);
            //??????????????????
            Inmaillist mail = imdao.findOne(lid);
            if (!StringUtil.isEmpty(request.getParameter("huifu"))) {
                huifu = request.getParameter("huifu");
                model.addAttribute("title", huifu + mail.getMailTitle());
                model.addAttribute("content", mail.getContent());

            } else {
                model.addAttribute("title", mail.getMailTitle());
                model.addAttribute("content", mail.getContent());
            }
            model.addAttribute("status", sdao.findOne(mail.getMailStatusid()));
            model.addAttribute("type", tydao.findOne(mail.getMailType()));
            model.addAttribute("id", "??????");

        } else {

            List<SystemTypeList> typelist = tydao.findByTypeModel("aoa_in_mail_list");
            List<SystemStatusList> statuslist = sdao.findByStatusModel("aoa_in_mail_list");
            model.addAttribute("typelist", typelist);
            model.addAttribute("statuslist", statuslist);
            model.addAttribute("id", "??????");

        }
        //?????????????????????????????????????????????
        List<Mailnumber> mailnum = mndao.findByStatusAndMailUserId(1L, mu);
        proservice.user(page, size, model);
        model.addAttribute("mailnum", mailnum);

        return "mail/wirtemail";
    }

    /**
     * ????????????
     *
     * @throws IOException
     * @throws IllegalStateException
     */
    @RequestMapping("pushmail")
    public String push(@RequestParam("file") MultipartFile file, HttpServletRequest request, @Valid Inmaillist mail, BindingResult br, @SessionAttribute("userId") Long userId) throws IllegalStateException, IOException {
        User tu = udao.findOne(userId);
        String name = null;
        Attachment attaid = null;
        Mailnumber number = null;
        StringTokenizer st = null;
        ResultVO res = BindingResultVOUtil.hasErrors(br);
        if (!ResultEnum.SUCCESS.getCode().equals(res.getCode())) {
            List<Object> list = new MapToList<>().mapToList(res.getData());
            request.setAttribute("errormess", list.get(0).toString());
        } else {
            if (!StringUtil.isEmpty(request.getParameter("fasong"))) {
                name = request.getParameter("fasong");
            }


            if (!StringUtil.isEmpty(name)) {
                if (!StringUtil.isEmpty(file.getOriginalFilename())) {
                    attaid = mservice.upload(file, tu);
                    attaid.setModel("mail");
                    AttDao.save(attaid);
                }
                //????????????
                mail.setPush(true);
            } else {
                //?????????
                mail.setInReceiver(null);
            }

            mail.setMailFileid(attaid);
            mail.setMailCreateTime(new Date());
            mail.setMailUserid(tu);
            if (!mail.getInmail().equals(0)) {
                number = mndao.findOne(mail.getInmail());
                mail.setMailNumberid(number);
            }
            //?????????
            Inmaillist imail = imdao.save(mail);

            if (!StringUtil.isEmpty(name)) {
                if (mservice.isContainChinese(mail.getInReceiver())) {

                    // ?????????????????????
                    StringTokenizer st2 = new StringTokenizer(mail.getInReceiver(), ";");
                    while (st2.hasMoreElements()) {
                        User reciver = udao.findid(st2.nextToken());
                        Mailreciver mreciver = new Mailreciver();
                        mreciver.setMailId(imail);
                        mreciver.setReciverId(reciver);
                        mrdao.save(mreciver);
                    }
                } else {
                    if (mail.getInReceiver().contains(";")) {
                        st = new StringTokenizer(mail.getInReceiver(), ";");
                    } else {
                        st = new StringTokenizer(mail.getInReceiver(), "???");
                    }

                    while (st.hasMoreElements()) {
                        if (!StringUtil.isEmpty(file.getOriginalFilename())) {

                            //???????????? ????????????????????????????????????
                            //assert attaid != null;
                            mservice.pushmail(number.getMailAccount(), number.getPassword(), st.nextToken(), number.getMailUserName(), mail.getMailTitle(),
                                    mail.getContent(), attaid.getAttachmentPath(), attaid.getAttachmentName());

                        }
                        else {
                            mservice.pushmail(number.getMailAccount(), number.getPassword(), st.nextToken(), number.getMailUserName(), mail.getMailTitle(),
                                    mail.getContent(), null, null);
                        }
                    }
                }

            }
        }
        return "redirect:/mail";
    }

    /**
     * ??????????????????
     */
    @RequestMapping("names")
    public String serch(Model model, HttpServletRequest req, @SessionAttribute("userId") Long userId,
                        @RequestParam(value = "page", defaultValue = "0") int page,
                        @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pa = new PageRequest(page, size);
        String name = null;
        String qufen = null;
        Page<User> pageuser = null;
        List<User> userlist = null;

        if (!StringUtil.isEmpty(req.getParameter("title"))) {
            name = req.getParameter("title").trim();
        }
        if (!StringUtil.isEmpty(req.getParameter("qufen"))) {
            qufen = req.getParameter("qufen").trim();

            System.out.println("111");
            if (StringUtil.isEmpty(name)) {
                // ???????????????????????????
                pageuser = udao.findByFatherId(userId, pa);
            } else {
                // ??????????????????????????????
                pageuser = udao.findbyFatherId(name, userId, pa);
            }

        } else {
            System.out.println("222");
            if (StringUtil.isEmpty(name)) {
                //?????????????????????
                pageuser = udao.findAll(pa);
            } else {
                pageuser = udao.findbyUserNameLike(name, pa);
            }
        }
        userlist = pageuser.getContent();
        // ???????????????
        Iterable<Dept> deptlist = ddao.findAll();
        // ????????????
        Iterable<Position> poslist = pdao.findAll();
        model.addAttribute("emplist", userlist);
        model.addAttribute("page", pageuser);
        model.addAttribute("deptlist", deptlist);
        model.addAttribute("poslist", poslist);
        model.addAttribute("url", "names");

        return "common/recivers";

    }

    /**
     * ????????????
     */
    @RequestMapping("amail")
    public String index3(HttpServletRequest req, @SessionAttribute("userId") Long userId, Model model,
                         @RequestParam(value = "page", defaultValue = "0") int page,
                         @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pa = new PageRequest(page, size);
        User mu = udao.findOne(userId);
        String mess = req.getParameter("title");

        Page<Pagemail> pagelist = null;
        Page<Inmaillist> pagemail = null;
        List<Map<String, Object>> maillist = null;
        if (("?????????").equals(mess)) {
            //???????????????
            pagelist = mservice.recive(page, size, mu, null, mess);
            maillist = mservice.mail(pagelist);
        } else if (("?????????").equals(mess)) {
            pagemail = mservice.inmail(page, size, mu, null, mess);
            maillist = mservice.maillist(pagemail);
        } else if (("?????????").equals(mess)) {
            pagemail = mservice.inmail(page, size, mu, null, mess);
            maillist = mservice.maillist(pagemail);
        } else {
            //?????????
            //???????????????
            pagelist = mservice.recive(page, size, mu, null, mess);
            maillist = mservice.mail(pagelist);

        }

        if (!Objects.isNull(pagelist)) {
            model.addAttribute("page", pagelist);
        } else {
            model.addAttribute("page", pagemail);

        }
        model.addAttribute("sort", "&title=" + mess);
        model.addAttribute("maillist", maillist);
        model.addAttribute("url", "mailtitle");
        model.addAttribute("mess", mess);
        return "mail/allmail";
    }

    /**
     * ????????????
     */
    @RequestMapping("smail")
    public String index4(HttpServletRequest req, @SessionAttribute("userId") Long userId, Model model) {
        User mu = udao.findOne(userId);
        //??????id
        Long id = Long.parseLong(req.getParameter("id"));
        //title
        String title = req.getParameter("title");
        //?????????????????????
        if (("?????????").equals(title) || ("?????????").equals(title)) {
            Mailreciver mailr = mrdao.findbyReciverIdAndmailId(mu, id);
            mailr.setRead(true);
            mrdao.save(mailr);
        }

        //?????????????????????
        Inmaillist mail = imdao.findOne(id);
        String filetype = null;
        if (!Objects.isNull(mail.getMailFileid())) {
            String filepath = mail.getMailFileid().getAttachmentPath();
            System.out.println(filepath);
            if (mail.getMailFileid().getAttachmentType().startsWith("image")) {

                filetype = "img";
            } else {
                filetype = "appli";

            }
            model.addAttribute("filepath", filepath);
            model.addAttribute("filetype", filetype);
        }

        User pushuser = udao.findOne(mail.getMailUserid().getUserId());
        model.addAttribute("pushname", pushuser.getUserName());
        model.addAttribute("mail", mail);
        model.addAttribute("mess", title);
        model.addAttribute("file", mail.getMailFileid());

        return "mail/seemail";
    }

    /**
     *
     */
    @RequestMapping("refresh")
    public String refresh(HttpServletRequest req, @SessionAttribute("userId") Long userId, Model model,
                          @RequestParam(value = "page", defaultValue = "0") int page,
                          @RequestParam(value = "size", defaultValue = "10") int size) {
        //????????????
        User user = udao.findOne(userId);
        String title = req.getParameter("title");
        Page<Pagemail> pagelist = null;
        List<Map<String, Object>> maillist = null;
        //??????????????????id
        String ids = req.getParameter("ids");

        StringTokenizer st = new StringTokenizer(ids, ",");
        while (st.hasMoreElements()) {
            //??????????????????????????????????????????
            Mailreciver mailr = mrdao.findbyReciverIdAndmailId(user, Long.parseLong(st.nextToken()));
            if (!Objects.isNull(mailr)) {
                mailr.setDel(false);
                mrdao.save(mailr);
            } else {
                return "redirect:/notlimit";
            }
        }
        //???????????????
        pagelist = mservice.recive(page, size, user, null, title);
        maillist = mservice.mail(pagelist);

        model.addAttribute("page", pagelist);
        model.addAttribute("maillist", maillist);
        model.addAttribute("url", "mailtitle");
        model.addAttribute("mess", title);

        return "mail/mailbody";

    }
}

