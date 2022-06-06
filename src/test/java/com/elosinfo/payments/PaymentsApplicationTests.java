package com.elosinfo.payments;

import com.elosinfo.payments.controller.CategoryController;
import com.elosinfo.payments.controller.PaymentController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PaymentsApplicationTests {
	@Autowired
	private PaymentController paymentController;
	@Autowired
	private CategoryController categoryController;

	@Test
	void contextLoads() {
	}

	@Test
	private void validaPaymentCriado(){

	}

}
