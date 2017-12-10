package starrail.payment.domain;

public class PaymentVO {

	private int pay_no;
	private int res_no;
	private String pay_card;
	private int pay_amount;
	public int getPay_no() {
		return pay_no;
	}
	public void setPay_no(int pay_no) {
		this.pay_no = pay_no;
	}
	public int getRes_no() {
		return res_no;
	}
	public void setRes_no(int res_no) {
		this.res_no = res_no;
	}
	public String getPay_card() {
		return pay_card;
	}
	public void setPay_card(String pay_card) {
		this.pay_card = pay_card;
	}
	public int getPay_amount() {
		return pay_amount;
	}
	public void setPay_amount(int pay_amount) {
		this.pay_amount = pay_amount;
	}
	
	
}
