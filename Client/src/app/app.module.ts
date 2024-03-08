import { NgModule } from '@angular/core';
import {AppComponent} from "./app.component";
import {LoginBusinessesComponent} from "./login-businesses/login-businesses.component";
import {RegisterComponent} from "./register/register.component";
import {LoginPersonalsComponent} from "./login-personals/login-personals.component";
import {LoginProfessionalsComponent} from "./login-professionals/login-professionals.component";
import {BusinessComponent} from "./business/business.component";
import {CreateAccountPageComponent} from "./create-account-page/create-account-page.component";
import {PersonalFormComponent} from "./personal-form/personal-form.component";
import {BusinessFormComponent} from "./business-form/business-form.component";
import {ProfessionalFormComponent} from "./professional-form/professional-form.component";
import {ProfessionalComponent} from "./professional/professional.component";
import {PersonalComponent} from "./personal/personal.component";
import {SalesComponent} from "./sales/sales.component";
import {SubscriptionComponent} from "./subscription/subscription.component";
import {PersonalTabComponent} from "./personal-tab/personal-tab.component";
import {ProfessionalTabComponent} from "./professional-tab/professional-tab.component";
import {BusinessTabComponent} from "./business-tab/business-tab.component";
import {SupportComponent} from "./support/support.component";
import {AboutUsComponent} from "./about-us/about-us.component";
import {ConntactComponent} from "./conntact/conntact.component";
import {FgsComponent} from "./fgs/fgs.component";
import {MeetingComponent} from "./meeting/meeting.component";
import {ReceiptComponent} from "./receipt/receipt.component";
import {InvoiceComponent} from "./invoice/invoice.component";
import {SidebarPersonalComponent} from "./sidebar-personal/sidebar-personal.component";
import {DashoardPersonalComponent} from "./dashoard-personal/dashoard-personal.component";
import {CreditCardComponent} from "./credit-cardPersonal/credit-card.component";
import {HistoryComponent} from "./historyPersonal/history.component";
import {AccountComponent} from "./accountPersonal/account.component";
import {TransferComponent} from "./transferPersonal/transfer.component";
import {RibPageComponent} from "./rib-pagePersonal/rib-page.component";
import {AccountsPageComponent} from "./accounts-pagePersonal/accounts-page.component";
import {ShowOneAccountComponent} from "./show-one-accountPersonal/show-one-account.component";
import {RequestMoneyComponent} from "./request-moneyPersonal/request-money.component";
import {HistoryPDFComponent} from "./history-pdfPersonal/history-pdf.component";
import {SidebarProfessionalComponent} from "./sidebar-Professional/sidebar-Professional.component";
import {DashoardProfessionalComponent} from "./dashoard-Professional/dashoard-Professional.component";
import {CreditCardProfessionalComponent} from "./credit-cardProfessional/credit-cardProfessional.component";
import {HistoryProfessionalComponent} from "./historyProfessional/historyProfessional.component";
import {AccountProfessionalComponent} from "./accountProfessional/accountProfessional.component";
import {TransferProfessionalComponent} from "./transferProfessional/transferProfessional.component";
import {RibPageProfessionalComponent} from "./rib-pageProfessional/rib-pageProfessional.component";
import {AccountsPageProfessionalComponent} from "./accounts-pageProfessional/accounts-pageProfessional.component";
import {
  ShowOneAccountProfessionalComponent
} from "./show-one-accountProfessional/show-one-accountProfessional.component";
import {RequestMoneyProfessionalComponent} from "./request-moneyProfessional/request-moneyProfessional.component";
import {HistoryPdfProfessionalComponent} from "./history-pdfProfessional/history-pdfProfessional.component";
import {SidebarBusinessComponent} from "./sidebar-Business/sidebar-Business.component";
import {DashoardBusinessComponent} from "./dashoard-Business/dashoard-Business.component";
import {CreditCardBusinessComponent} from "./credit-cardBusiness/credit-cardBusiness.component";
import {HistoryBusinesscomponent} from "./historyBusiness/historyBusinesscomponent";
import {AccountBusinessComponent} from "./accountBusiness/accountBusiness.component";
import {TransferBusinessComponent} from "./transferBusiness/transferBusiness.component";
import {RibPageBusinessComponent} from "./rib-pageBusiness/rib-pageBusiness.component";
import {AccountsPageBusinessComponent} from "./accounts-pageBusiness/accounts-pageBusiness.component";
import {ShowOneAccountBusinessComponent} from "./show-one-accountBusiness/show-one-accountBusiness.component";
import {RequestMoneyBusinessComponent} from "./request-moneyBusiness/request-moneyBusiness.component";
import {HistoryPdfBusinessComponent} from "./history-pdfBusiness/history-pdfBusiness.component";
import {SidebarComponent} from "./sidebar/sidebar.component";
import {DashboardadminComponent} from "./dashboardadmin/dashboardadmin.component";
import {PersonelAdminComponent} from "./personelAdmin/personelAdmin.component";
import {BusinessAdminComponent} from "./businessAdmin/businessAdmin.component";
import {ProfessionalAdminComponent} from "./professionalAdmin/professionalAdmin.component";
import {PendingAcountPersonelComponent} from "./pending-acount-personel/pending-acount-personel.component";
import {ActivedaccountPersonelComponent} from "./activedaccount-personel/activedaccount-personel.component";
import {SuspentedaccountPersonelComponent} from "./suspentedaccount-personel/suspentedaccount-personel.component";
import {DeletedaccountPersonelComponent} from "./deletedaccount-personel/deletedaccount-personel.component";
import {DeletedaccountProfessionalComponent} from "./deletedaccount-professional/deletedaccount-professional.component";
import {
  SuspentedaccountProfessionalComponent
} from "./suspentedaccount-professional/suspentedaccount-professional.component";
import {ActivedaccountProfessionalComponent} from "./activedaccount-professional/activedaccount-professional.component";
import {SuspentedaccountBusinessComponent} from "./suspentedaccount-business/suspentedaccount-business.component";
import {ActivedaccountbusinessComponent} from "./activedaccountbusiness/activedaccountbusiness.component";
import {DeletedaccountbusinessComponent} from "./deletedaccountbusiness/deletedaccountbusiness.component";
import {CreditCardSavingComponent} from "./credit-cardSaving/credit-cardSaving.component";
import {InvoicePageComponent} from "./invoice-page/invoice-page.component";
import {SidebarBusinessToolsComponent} from "./sidebar-BusinessTools/sidebar-BusinessTools.component";
import {AllInvoicesComponent} from "./all-invoices/all-invoices.component";
import {InvoiceFormComponent} from "./invoice-form/invoice-form.component";
import {InvoiceFormPageComponent} from "./invoice-form-page/invoice-form-page.component";
import {AllproductsComponent} from "./allproducts/allproducts.component";
import {AllproductsPageComponent} from "./allproducts-page/allproducts-page.component";
import {ProductFormComponent} from "./product-form/product-form.component";
import {ProductFormPageComponent} from "./product-form-page/product-form-page.component";
import {BusinessToolsComponent} from "./business-tools/business-tools.component";
import {OneProductComponent} from "./one-product/one-product.component";
import {AddToInvoicePageComponent} from "./AddToInvoice-page/AddToInvoice-page.component";
import {AddToInvoiceAllproductsComponent} from "./AddToInvoiceAllproducts/AddToInvoiceAllproducts.component";
import {BrowserModule} from "@angular/platform-browser";
import {AppRoutingModule} from "./app-routing.module";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {DashboardComponent} from "./pages/dashboard/dashboard.component";
import {BarChartComponent} from "./bar-chart/bar-chart.component";
import {LineChartComponent} from "./line-chart/line-chart.component";



function LoginPersonalComponent() {
}
@NgModule({
  bootstrap: [AppComponent],
  declarations: [
    AppComponent,

    LoginBusinessesComponent,
    RegisterComponent,
    LoginPersonalsComponent,
    LoginProfessionalsComponent,
    BusinessComponent,
    CreateAccountPageComponent,
    PersonalFormComponent,
    BusinessFormComponent,
    ProfessionalFormComponent,

    ProfessionalComponent,
    PersonalComponent,
    SalesComponent,
    SubscriptionComponent,
    PersonalTabComponent,
    ProfessionalTabComponent,
    BusinessTabComponent,
    SupportComponent,
    AboutUsComponent,
    FgsComponent,
    ConntactComponent,
    MeetingComponent,
    ReceiptComponent,
    InvoiceComponent,
    SidebarPersonalComponent,
    DashoardPersonalComponent,
    CreditCardComponent,
    HistoryComponent,
    AccountComponent,
    TransferComponent,


    RibPageComponent,
    AccountsPageComponent,
    ShowOneAccountComponent,
    RequestMoneyComponent,
    HistoryPDFComponent,
    SidebarProfessionalComponent,
    DashoardProfessionalComponent,
    CreditCardProfessionalComponent,
    HistoryProfessionalComponent,
    AccountProfessionalComponent,
    TransferProfessionalComponent,
    RibPageProfessionalComponent,
    AccountsPageProfessionalComponent,
    ShowOneAccountProfessionalComponent,
    RequestMoneyProfessionalComponent,
    HistoryPdfProfessionalComponent,

    SidebarBusinessComponent,
    DashoardBusinessComponent,
    CreditCardBusinessComponent,
    HistoryBusinesscomponent,
    AccountBusinessComponent,
    TransferBusinessComponent,
    RibPageBusinessComponent,
    AccountsPageBusinessComponent,
    ShowOneAccountBusinessComponent,
    RequestMoneyBusinessComponent,
    HistoryPdfBusinessComponent,

    SidebarComponent,

    DashboardadminComponent,
    PersonelAdminComponent,
    BusinessAdminComponent,
    ProfessionalAdminComponent,
    PendingAcountPersonelComponent,
    ActivedaccountPersonelComponent,
    SuspentedaccountPersonelComponent,
    DeletedaccountPersonelComponent,
    DeletedaccountProfessionalComponent,
    SuspentedaccountProfessionalComponent,
    ActivedaccountProfessionalComponent,
    SuspentedaccountBusinessComponent,
    ActivedaccountbusinessComponent,
    DeletedaccountbusinessComponent,
    CreditCardSavingComponent,

    InvoiceComponent,
    InvoicePageComponent,
    SidebarBusinessToolsComponent,
    AllInvoicesComponent,
    InvoiceFormComponent,
    InvoiceFormPageComponent,
    AllproductsComponent,
    AllproductsPageComponent,
    ProductFormComponent,
    ProductFormPageComponent,
    BusinessToolsComponent,
    OneProductComponent,
    AddToInvoicePageComponent,
    AddToInvoiceAllproductsComponent,
    DashboardComponent,
    LineChartComponent,
    BarChartComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule, HttpClientModule,
    ReactiveFormsModule, FormsModule,
  ],
  providers: []
})
export class AppModule { }
