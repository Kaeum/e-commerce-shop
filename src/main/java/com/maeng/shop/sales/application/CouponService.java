package com.maeng.shop.sales.application;

import com.maeng.shop.sales.domain.Coupon;
import com.maeng.shop.sales.domain.Customer;
import com.maeng.shop.sales.domain.CustomerCouponMapping;
import com.maeng.shop.sales.domain.Supplier;
import com.maeng.shop.sales.dto.CreateCouponRequest;
import com.maeng.shop.sales.exception.NoCouponException;
import com.maeng.shop.sales.exception.NoSupplierException;
import com.maeng.shop.sales.repository.CouponRepository;
import com.maeng.shop.sales.repository.CustomerCouponRepository;
import com.maeng.shop.sales.repository.SupplierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CouponService {
    private final CustomerService customerService;
    private final CustomerCouponRepository customerCouponRepository;
    private final CouponRepository couponRepository;
    private final SupplierRepository supplierRepository;

    public CouponService(
            final CustomerService customerService,
            final CustomerCouponRepository customerCouponRepository,
            final CouponRepository couponRepository,
            final SupplierRepository supplierRepository) {
        this.customerService = customerService;
        this.customerCouponRepository = customerCouponRepository;
        this.couponRepository = couponRepository;
        this.supplierRepository = supplierRepository;
    }

    @Transactional
    public Long createCoupon(final CreateCouponRequest couponRequest) {
        if(couponRequest.isBrandCoupon()) {
            Supplier supplier = supplierRepository.findById(couponRequest.getSupplierId())
                    .orElseThrow(NoSupplierException::new);

            Coupon coupon = couponRequest.toBrandCoupon(supplier);
            couponRepository.save(coupon);
            return coupon.getId();
        }

        Coupon coupon = couponRequest.toMemberLevelCoupon();
        couponRepository.save(coupon);
        return coupon.getId();
    }

    @Transactional
    public void issueCoupon(final Long customerId, final Long couponId) {
        Customer customer = customerService.getCustomer(customerId);
        Coupon coupon = couponRepository.findById(couponId).orElseThrow(NoCouponException::new);

        CustomerCouponMapping customerCouponMapping = new CustomerCouponMapping(customer, coupon);
        customerCouponRepository.save(customerCouponMapping);
    }
}
