import java.util.Set;

import com.ib.client.CommissionReport;
import com.ib.client.Contract;
import com.ib.client.ContractDetails;
import com.ib.client.DeltaNeutralContract;
import com.ib.client.EClientSocket;
import com.ib.client.EJavaSignal;
import com.ib.client.EReaderSignal;
import com.ib.client.EWrapper;
import com.ib.client.Execution;
import com.ib.client.Order;
import com.ib.client.OrderState;
import com.ib.client.SoftDollarTier;
import com.ib.client.TickType;

public class MyWrapper2 implements EWrapper {

    static protected int currentOrderId = -1;
    static private EWrapper wrapper = new MyWrapper2();
    static private EJavaSignal readerSignal = new EJavaSignal();
    static private EClientSocket clientSocket = new EClientSocket(wrapper, readerSignal);

    public MyWrapper2() {
     
    }

    public void tickPrice( int tickerId, int field, double price, int canAutoExecute) {
	System.out.println("Tick Price. Ticker Id:"+tickerId+", Field: "+field+", Price: "+price+", CanAutoExecute: "+canAutoExecute);

    }

    public void tickSize( int tickerId, int field, int size) {
       System.out.println("Tick Size. Ticker Id:" + tickerId + ", Field: " + field + ", Size: " + size);
    }
    public void tickOptionComputation( int tickerId, int field, double impliedVol, double delta, double optPrice, double pvDividend, double gamma, double vega, double theta, double undPrice) {
       System.out.println("TickOptionComputation. TickerId: "+tickerId+", field: "+field+", ImpliedVolatility: "+impliedVol+", Delta: "+delta
                +", OptionPrice: "+optPrice+", pvDividend: "+pvDividend+", Gamma: "+gamma+", Vega: "+vega+", Theta: "+theta+", UnderlyingPrice: "+undPrice);

    }
    public void tickGeneric(int tickerId, int tickType, double value) {
       System.out.println("Tick Generic. Ticker Id:" + tickerId + ", Field: " + TickType.getField(tickType) + ", Value: " + value);

    }
    public void tickString(int tickerId, int tickType, String value) {
	System.out.println("Tick string. Ticker Id:" + tickerId + ", Type: " + tickType + ", Value: " + value);

    }

    public void tickEFP(int tickerId, int tickType, double basisPoints, String formattedBasisPoints, double impliedFuture, int holdDays, String futureLastTradeDate, double dividendImpact, double dividendsToLastTradeDate) {
       System.out.println("TickEFP. "+tickerId+", Type: "+tickType+", BasisPoints: "+basisPoints+", FormattedBasisPoints: "+
			formattedBasisPoints+", ImpliedFuture: "+impliedFuture+", HoldDays: "+holdDays+", FutureLastTradeDate: "+futureLastTradeDate+
			", DividendImpact: "+dividendImpact+", DividendsToLastTradeDate: "+dividendsToLastTradeDate);
    }

    public void orderStatus( int orderId, String status, double filled, double remaining, double avgFillPrice, int permId, int parentId, double lastFillPrice, int clientId, String whyHeld) {
       System.out.println("OrderStatus. Id: "+orderId+", Status: "+status+", Filled"+filled+", Remaining: "+remaining
                +", AvgFillPrice: "+avgFillPrice+", PermId: "+permId+", ParentId: "+parentId+", LastFillPrice: "+lastFillPrice+
                ", ClientId: "+clientId+", WhyHeld: "+whyHeld);


    }

    public void openOrder( int orderId, Contract contract, Order order, OrderState orderState) {
       System.out.println("OpenOrder. ID: "+orderId+", "+contract.symbol()+", "+contract.secType()+" @ "+contract.exchange()+": "+
			order.action()+", "+order.orderType()+" "+order.totalQuantity()+", "+orderState.status());

    }

    public void openOrderEnd() {
       System.out.println("OpenOrderEnd");

    }
    
   public void updateAccountValue(String key, String value, String currency, String accountName) {
       System.out.println("UpdateAccountValue. Key: " + key + ", Value: " + value + ", Currency: " + currency + ", AccountName: " + accountName);
    }

    public void updatePortfolio(Contract contract, double position, double marketPrice, double marketValue, double averageCost, double unrealizedPNL, double realizedPNL, String accountName) {
       System.out.println("UpdatePortfolio. "+contract.symbol()+", "+contract.secType()+" @ "+contract.exchange()
                +": Position: "+position+", MarketPrice: "+marketPrice+", MarketValue: "+marketValue+", AverageCost: "+averageCost
                +", UnrealisedPNL: "+unrealizedPNL+", RealisedPNL: "+realizedPNL+", AccountName: "+accountName);
    }
    
    public void updateAccountTime(String timeStamp) {
       System.out.println("UpdateAccountTime. Time: " + timeStamp+"\n");

    }
    
    public void accountDownloadEnd(String accountName) {
       System.out.println("Account download finished: "+accountName+"\n");
    }
    
    public void nextValidId( int orderId) {
       System.out.println("Next Valid Id: ["+orderId+"]");
       currentOrderId = orderId;
    }
    
    public void contractDetails(int reqId, ContractDetails contractDetails) {
       System.out.println("ContractDetails. ReqId: ["+reqId+"] - ["+contractDetails.contract().symbol()+"], ["+contractDetails.contract().secType()+"], ConId: ["+contractDetails.contract().conid()+"] @ ["+contractDetails.contract().exchange()+"]");
    }
    
    public void bondContractDetails(int reqId, ContractDetails contractDetails) {
       System.out.println("bondContractDetails");
    }
    
    public void contractDetailsEnd(int reqId) {
       System.out.println("ContractDetailsEnd. "+reqId+"\n");
    }
    
    public void execDetails( int reqId, Contract contract, Execution execution) {
       System.out.println("ExecDetails. "+reqId+" - ["+contract.symbol()+"], ["+contract.secType()+"], ["+contract.currency()+"], ["+execution.execId()+"], ["+execution.orderId()+"], ["+execution.shares()+"]");
    }
    
    public void execDetailsEnd( int reqId) {
       System.out.println("ExecDetailsEnd. "+reqId+"\n");
    }
    
    public void updateMktDepth( int tickerId, int position, int operation, int side, double price, int size) {
       System.out.println("UpdateMarketDepth. "+tickerId+" - Position: "+position+", Operation: "+operation+", Side: "+side+", Price: "+price+", Size: "+size+"");
    }
    public void updateMktDepthL2( int tickerId, int position, String marketMaker, int operation, int side, double price, int size){
       System.out.println("updateMktDepthL2");
    }
    public void updateNewsBulletin( int msgId, int msgType, String message, String origExchange){
       System.out.println("News Bulletins. "+msgId+" - Type: "+msgType+", Message: "+message+", Exchange of Origin: "+origExchange+"\n");
    }
    public void managedAccounts( String accountsList){
       System.out.println("Account list: " +accountsList);
    }
    public void receiveFA(int faDataType, String xml){
       System.out.println("Receing FA: "+faDataType+" - "+xml);
    }
    public void historicalData(int reqId, String date, double open, double high, double low, double close, int volume, int count, double WAP, boolean hasGaps){
       System.out.println("HistoricalData. "+reqId+" - Date: "+date+", Open: "+open+", High: "+high+", Low: "+low+", Close: "+close+", Volume: "+volume+", Count: "+count+", WAP: "+WAP+", HasGaps: "+hasGaps);
    }
    public void scannerParameters(String xml){
       System.out.println("ScannerParameters. "+xml+"\n");
    }
    public void scannerData(int reqId, int rank, ContractDetails contractDetails, String distance, String benchmark, String projection, String legsStr){
       System.out.println("ScannerData. "+reqId+" - Rank: "+rank+", Symbol: "+contractDetails.contract().symbol()+", SecType: "+contractDetails.contract().secType()+", Currency: "+contractDetails.contract().currency()
                +", Distance: "+distance+", Benchmark: "+benchmark+", Projection: "+projection+", Legs String: "+legsStr);
    }
    public void scannerDataEnd(int reqId){
       System.out.println("ScannerDataEnd. "+reqId);
    }
    public void realtimeBar(int reqId, long time, double open, double high, double low, double close, long volume, double wap, int count){
       System.out.println("RealTimeBars. " + reqId + " - Time: " + time + ", Open: " + open + ", High: " + high + ", Low: " + low + ", Close: " + close + ", Volume: " + volume + ", Count: " + count + ", WAP: " + wap);
    }
    public void currentTime(long time){
       System.out.println("currentTime");
    }
    public void fundamentalData(int reqId, String data){
       System.out.println("FundamentalData. ReqId: ["+reqId+"] - Data: ["+data+"]");
    }
    public void deltaNeutralValidation(int reqId, DeltaNeutralContract underComp){
       System.out.println("deltaNeutralValidation");
    }
    public void tickSnapshotEnd(int reqId){
       System.out.println("TickSnapshotEnd: "+reqId);
    }
    public void marketDataType(int reqId, int marketDataType){
       System.out.println("MarketDataType. ["+reqId+"], Type: ["+marketDataType+"]\n");
    }
    public void commissionReport(CommissionReport commissionReport){
       	System.out.println("CommissionReport. ["+commissionReport.m_execId+"] - ["+commissionReport.m_commission+"] ["+commissionReport.m_currency+"] RPNL ["+commissionReport.m_realizedPNL+"]");
    }
    public void position(String account, Contract contract, double pos, double avgCost){
       	System.out.println("Position. "+account+" - Symbol: "+contract.symbol()+", SecType: "+contract.secType()+", Currency: "+contract.currency()+", Position: "+pos+", Avg cost: "+avgCost);
    }
    public void positionEnd(){
       	System.out.println("PositionEnd \n");
    }
    public void accountSummary(int reqId, String account, String tag, String value, String currency){
       System.out.println("Acct Summary. ReqId: " + reqId + ", Acct: " + account + ", Tag: " + tag + ", Value: " + value + ", Currency: " + currency);
    }
    public void accountSummaryEnd(int reqId){
      System.out.println("AccountSummaryEnd. Req Id: "+reqId+"\n");
    }
    public void verifyMessageAPI( String apiData){
       System.out.println("not Implemented");
    }
    public void verifyCompleted( boolean isSuccessful, String errorText){
       System.out.println("verifyCompleted");
    }
    public void verifyAndAuthMessageAPI( String apiData, String xyzChallange){
       System.out.println("verifyAndAuthMessageAPI");
    }
    public void verifyAndAuthCompleted( boolean isSuccessful, String errorText){
       System.out.println("verifyAndAuthCompleted");
    }
    public void displayGroupList( int reqId, String groups){
       System.out.println("not Implemented");
    }
    public void displayGroupUpdated( int reqId, String contractInfo){
       System.out.println("not Implemented");
    }

    public void error( Exception e){
       System.out.println("Exception: "+e.getMessage());
    }
    public void error( String str){
       System.out.println("Error STR");
    }
    public void error(int id, int errorCode, String errorMsg){
       System.out.println("Error. Id: " + id + ", Code: " + errorCode + ", Msg: " + errorMsg + "\n");
    }
    public void connectionClosed(){
       System.out.println("connection closed");
    }
    public void connectAck(){
       	if (clientSocket.isAsyncEConnect()) {
			System.out.println("Acknowledging connection");
			clientSocket.startAPI();
		}
    }
    public void positionMulti( int reqId, String account, String modelCode, Contract contract, double pos, double avgCost){
       System.out.println("not Implemented");
    }
    public void positionMultiEnd( int reqId){
       System.out.println("not Implemented");
    }
    public void accountUpdateMulti( int reqId, String account, String modelCode, String key, String value, String currency){
       System.out.println("not Implemented");
    }
    public void accountUpdateMultiEnd( int reqId){
       System.out.println("not Implemented");
    }
    public void securityDefinitionOptionalParameter(int reqId, String exchange, int underlyingConId, String tradingClass, String multiplier, Set<String> expirations, Set<Double> strikes){
       System.out.println("not Implemented");
    }
    public void securityDefinitionOptionalParameterEnd(int reqId){
       System.out.println("not Implemented");
    }
    public void softDollarTiers(int reqId, SoftDollarTier[] tiers){
       System.out.println("not Implemented");
    }
    

}
