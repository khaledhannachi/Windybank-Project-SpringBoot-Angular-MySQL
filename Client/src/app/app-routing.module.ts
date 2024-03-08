import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import {AboutUsComponent} from "./about-us/about-us.component";
import {HomeComponent} from "./home/home.component";
import {PersonalComponent} from "./personal/personal.component";
import {BusinessComponent} from "./business/business.component";
import {ProfessionalComponent} from "./professional/professional.component";
import {PersonalFormComponent} from "./personal-form/personal-form.component";
import {BusinessFormComponent} from "./business-form/business-form.component";
import {ProfessionalFormComponent} from "./professional-form/professional-form.component";
import {NavbarComponent} from "./navbar/navbar.component";
import {FooterComponent} from "./footer/footer.component";
import {CreateAccountPageComponent} from "./create-account-page/create-account-page.component";
import {RegisterComponent} from "./register/register.component";
import {PersonalTabComponent} from "./personal-tab/personal-tab.component";
import {ProfessionalTabComponent} from "./professional-tab/professional-tab.component";
import {BusinessTabComponent} from "./business-tab/business-tab.component";
import {SupportComponent} from "./support/support.component";
import {DashoardPersonalComponent} from "./dashoard-personal/dashoard-personal.component";
import {CreditCardComponent} from "./credit-cardPersonal/credit-card.component";
import {TransferComponent} from "./transferPersonal/transfer.component";
import {RibPageComponent} from "./rib-pagePersonal/rib-page.component";
import {RequestMoneyComponent} from "./request-moneyPersonal/request-money.component";
import {AccountsPageComponent} from "./accounts-pagePersonal/accounts-page.component";
import {DashoardProfessionalComponent} from "./dashoard-Professional/dashoard-Professional.component";
import {CreditCardProfessionalComponent} from "./credit-cardProfessional/credit-cardProfessional.component";
import {TransferProfessionalComponent} from "./transferProfessional/transferProfessional.component";
import {RibPageProfessionalComponent} from "./rib-pageProfessional/rib-pageProfessional.component";
import {AccountsPageProfessionalComponent} from "./accounts-pageProfessional/accounts-pageProfessional.component";
import {RequestMoneyProfessionalComponent} from "./request-moneyProfessional/request-moneyProfessional.component";
import {DashoardBusinessComponent} from "./dashoard-Business/dashoard-Business.component";
import {CreditCardBusinessComponent} from "./credit-cardBusiness/credit-cardBusiness.component";
import {TransferBusinessComponent} from "./transferBusiness/transferBusiness.component";
import {RibPageBusinessComponent} from "./rib-pageBusiness/rib-pageBusiness.component";
import {AccountsPageBusinessComponent} from "./accounts-pageBusiness/accounts-pageBusiness.component";
import {RequestMoneyBusinessComponent} from "./request-moneyBusiness/request-moneyBusiness.component";
import {DeletedaccountbusinessComponent} from "./deletedaccountbusiness/deletedaccountbusiness.component";
import {DashboardadminComponent} from "./dashboardadmin/dashboardadmin.component";
import {PersonelAdminComponent} from "./personelAdmin/personelAdmin.component";
import {BusinessAdminComponent} from "./businessAdmin/businessAdmin.component";
import {ProfessionalAdminComponent} from "./professionalAdmin/professionalAdmin.component";
import {PendingAcountPersonelComponent} from "./pending-acount-personel/pending-acount-personel.component";
import {ActivedaccountPersonelComponent} from "./activedaccount-personel/activedaccount-personel.component";
import {SuspentedaccountPersonelComponent} from "./suspentedaccount-personel/suspentedaccount-personel.component";
import {DeletedaccountPersonelComponent} from "./deletedaccount-personel/deletedaccount-personel.component";
import {DeletedaccountProfessionalComponent} from "./deletedaccount-professional/deletedaccount-professional.component";
import {ActivedaccountProfessionalComponent} from "./activedaccount-professional/activedaccount-professional.component";
import {
  SuspentedaccountProfessionalComponent
} from "./suspentedaccount-professional/suspentedaccount-professional.component";
import {SuspentedaccountBusinessComponent} from "./suspentedaccount-business/suspentedaccount-business.component";
import {ActivedaccountbusinessComponent} from "./activedaccountbusiness/activedaccountbusiness.component";
import {AllInvoicesComponent} from "./all-invoices/all-invoices.component";
import {InvoicePageComponent} from "./invoice-page/invoice-page.component";
import {InvoiceFormPageComponent} from "./invoice-form-page/invoice-form-page.component";
import {AddToInvoicePageComponent} from "./AddToInvoice-page/AddToInvoice-page.component";
import {ProductFormPageComponent} from "./product-form-page/product-form-page.component";
import {BusinessToolsComponent} from "./business-tools/business-tools.component";
import {OneProductComponent} from "./one-product/one-product.component";
import {AllproductsPageComponent} from "./allproducts-page/allproducts-page.component";
import {DashboardComponent} from "./pages/dashboard/dashboard.component";



const routes: Routes = [
  {path: "", redirectTo:"/home", pathMatch: "full"},
  // {path: "login", component:LoginBusinessesComponent},
  {path: "register", component:RegisterComponent},
  {path:'',component:HomeComponent},
  {path:'home',component:HomeComponent},
  {path:'personal',component:PersonalComponent},
  {path:'business',component:BusinessComponent},
  {path:'professional',component:ProfessionalComponent},
  {path:'personalform',component:PersonalFormComponent},
  {path:'businessform',component:BusinessFormComponent},
  {path:'professionalform',component:ProfessionalFormComponent},
  {path:'about',component:AboutUsComponent},
  {path:'create',component:CreateAccountPageComponent},
  {path:'personaltab',component:PersonalTabComponent},
  {path:'professionaltab',component:ProfessionalTabComponent},
  {path:'businesstab',component:BusinessTabComponent},
  {path:'y',component:SupportComponent},
  {path:'Aboutus',component:AboutUsComponent},

  {path : "dashboardPersonal", component :DashoardPersonalComponent },
  {path : "card", component :CreditCardComponent },
  {path: "transfer", component:TransferComponent},
  {path: "rib", component:RibPageComponent},
  {path: "savingaccount", component:AccountsPageComponent},
  {path: "requestmoney", component:RequestMoneyComponent},

  {path : "dashboardProfessional", component :DashoardProfessionalComponent },
  {path : "cardProfessional", component :CreditCardProfessionalComponent},
  {path: "transferProfessional", component:TransferProfessionalComponent},
  {path: "ribProfessional", component:RibPageProfessionalComponent},
  {path: "savingaccountProfessional", component:AccountsPageProfessionalComponent},
  {path: "requestmoneyProfessional", component:RequestMoneyProfessionalComponent},

  {path : "dashboardBusiness", component :DashoardBusinessComponent},
  {path : "cardBusiness", component :CreditCardBusinessComponent},
  {path: "transferBusiness", component:TransferBusinessComponent},
  {path: "ribBusiness", component:RibPageBusinessComponent},
  {path: "savingaccountBusiness", component:AccountsPageBusinessComponent},
  {path: "requestmoneyBusiness", component:RequestMoneyBusinessComponent},

  {path:"admin",component:DashboardadminComponent},
  {path:"personelAdmin",component:PersonelAdminComponent},
  {path:"businessAdmin",component:BusinessAdminComponent},
  {path:"professionalAdmin",component:ProfessionalAdminComponent},
  {path:"pendingAccount",component:PendingAcountPersonelComponent},
  {path:"activedAccount",component:ActivedaccountPersonelComponent},
  {path:"suspendedAccount",component:SuspentedaccountPersonelComponent},
  {path:"deletedAccount",component:DeletedaccountPersonelComponent},
  {path:"deletedAccountProfessionel",component:DeletedaccountProfessionalComponent},
  {path:"activedAccountProfessionel",component:ActivedaccountProfessionalComponent},
  {path:"suspentedAccountProfessionel",component:SuspentedaccountProfessionalComponent},
  {path:"suspentedAccountBusiness",component:SuspentedaccountBusinessComponent},
  {path:"activedAccountBusiness",component:ActivedaccountbusinessComponent},
  {path:"deletedAccountbusiness",component:DeletedaccountbusinessComponent},
  {path:'invoicepage/:id',component:InvoicePageComponent},
  {path:'invoices',component:AllInvoicesComponent},
  {path:'newinvoice',component:InvoiceFormPageComponent},
  {path:'inventory/:id',component:AddToInvoicePageComponent},
  {path:'newproduct',component:ProductFormPageComponent},
  {path:'businesstools',component:BusinessToolsComponent},
  {path:'product',component:OneProductComponent},
  { path: "allproducts", component: AllproductsPageComponent },
  { path: "dashboardhome", component: DashboardComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  declarations: [
    NavbarComponent,
    FooterComponent
  ],
  exports: [RouterModule, NavbarComponent, FooterComponent]
})
export class AppRoutingModule { }
