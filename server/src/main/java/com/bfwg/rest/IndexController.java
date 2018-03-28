package com.bfwg.rest;

import org.springframework.stereotype.Controller;


@Controller
public class IndexController {

//    @Autowired
//    private PrimaryAccountRepo primaryAccountRepo;
//
//    @Autowired
//    private SavingAccountRepo savingAccountRepo;
//
//    @Autowired
//    private RatesService ratesService;
//
//    @GetMapping("/")
//    public String home() {
//        return "redirect:/index";
//    }
//
//    @GetMapping("/index")
//    public String user(Principal principal, Model model) {
//        model.addAttribute("primaryAccounts", primaryAccountRepo.findByUserUsernameOrderById(principal.getName()));
//        List<SavingAccount> savingAccountList = savingAccountRepo.findByUserUsernameOrderById(principal.getName());
//        if (savingAccountList.size() > 0) {
//            model.addAttribute("savingAccount", savingAccountList.get(0));
//        }
//        model.addAttribute("currency", ratesService.getRates(new Date()));
//        return "index";
//    }
//
//    @GetMapping("/primaryAccounts")
//    public String primaryAccounts(Principal principal, Model model) {
//        model.addAttribute("accountBalance", "Primary balance");
//        model.addAttribute("currency", true);
//        model.addAttribute("accounts", primaryAccountRepo.findByUserUsernameOrderById(principal.getName()));
//        model.addAttribute("detailsURL", "/account/primaryAccount");
//        model.addAttribute("referenceCssClass", "panel-info");
//        return "accountsView";
//    }
//
//    @GetMapping("/savingAccounts")
//    public String savingAccounts(Principal principal, Model model) {
//        model.addAttribute("accountBalance", "Saving balance");
//        model.addAttribute("currency", false);
//        model.addAttribute("accounts", savingAccountRepo.findByUserUsernameOrderById(principal.getName()));
//        model.addAttribute("detailsURL", "/account/savingAccount");
//        model.addAttribute("referenceCssClass", "panel-success");
//        return "accountsView";
//    }

}
