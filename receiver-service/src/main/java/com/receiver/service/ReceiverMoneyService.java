package com.receiver.service;

import com.receiver.entity.ReceiverAccountModel;
import com.receiver.exception.ReceiverException;
import com.receiver.reponsitory.ReceiverAccountRepository;
import com.receiver.sender.OrchestratorRequest;
import core.response.ErrorData;
import core.response.ResponseBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static core.response.ResponseStatus.MONEY_NOT_EMPTY;
import static core.response.ResponseStatus.SUCCESS;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiverMoneyService {

    final ReceiverAccountRepository receiverAccountRepository;

    public ResponseBody<Object> receiverMoney(OrchestratorRequest request){

        if(request.getAmount().isEmpty() && request.getAmount() == null){
            var errorMapping = ErrorData.builder()
                    .errorType(ErrorData.ERROR_TYPE_STB)
                    .errorKey1(ErrorData.ERROR_KEY1_SERV_REG)
                    .errorKey2(MONEY_NOT_EMPTY.getCode())
                    .build();
            throw new ReceiverException(HttpStatus.BAD_REQUEST,MONEY_NOT_EMPTY, errorMapping);

        }
        Double rs = receiverAccountRepository.findById(request.getUserReceiverId()).map(ReceiverAccountModel::getMoneyTotal).orElse(0.0d);

        ReceiverAccountModel receiverAccountModel = ReceiverAccountModel.builder()
                .userReceiverId(request.getUserReceiverId())
                .moneyTotal(Double.parseDouble(request.getAmount() + rs))
                .build();

        receiverAccountRepository.save(receiverAccountModel);

        ResponseBody<Object> response = new ResponseBody<>();

        response.setOperationSuccess(SUCCESS , null);
        return response;
    }

//    private void failAndSendMail(RegisterSessionModel session, String productShelfId, String hitDesc, String mobileNo) {
//        try {
//            if (StringUtils.isBlank(mobileNo)) {
//                var contact = wrapperService.inquiryMobileEmail(
//                                InquiryMobileAndEmailRequest.builder()
//                                        .customerNumber(Long.parseLong(session.getCifNo()))
//                                        .build())
//                        .getResultData();
//
//                mobileNo = contact != null ? contact.getMobileNumber() : "";
//            }
//
//            var productShelf = depositServiceClient.getProductShelfDetail(
//                    productShelfId, principalService.getLanguage()).getResultData();
//
//            this.publishEmailVerifyNameScreeningMessage(session, mobileNo, productShelf, hitDesc);
//        } catch (Exception ex) {
//            log.error(String.format("Publish verify name screening has exception cause: %s message: %s",
//                    ExceptionUtils.getRootCause(ex), ex.getMessage()), loggingIndex.kv());
//        }
//
//        var errorMapping = ErrorData.builder()
//                .errorType(ErrorData.ERROR_TYPE_NMU)
//                .errorKey1(ErrorData.ERROR_KEY1_SERV_REG)
//                .errorKey2(VERIFY_AML_NOT_PASS.getCode())
//                .build();
//
//        throw new RegisterException(HttpStatus.OK, VERIFY_AML_NOT_PASS, errorMapping);
//    }
//
//    private void publishEmailVerifyNameScreeningMessage(RegisterSessionModel session, String mobileNo,
//                                                        ProductDetailResponse productShelf, String failReason) {
//
//        var message = SendEmailVerifyNameScreeningMessage.builder()
//                .email(corebankAmloEmail)
//                .device(this.getDeviceMappingModel(session.getDeviceName()))
//                .openningType(productShelf.getProductType())
//                .productName(principalService.getLanguage().equalsIgnoreCase("th")
//                        ? productShelf.getProductNameTh()
//                        : productShelf.getProductNameEn())
//                .failReason(failReason)
//                .natId(session.getNatId())
//                .thaiDob(FormatUtils.getBirthDateFormat(session.getBirthDay(), session.getBirthMonth(), session.getBirthYear(), true, principalService.getLanguage().equalsIgnoreCase("th")))
//                .citizenCardAddress("")
//                .mobileNo(mobileNo)
//                .fullName(session.getFirstNameTh()
//                        .concat(" ")
//                        .concat(this.getLastName(session.getMiddleNameTh(), session.getLastNameTh())))
//                .career("")
//                .officeAddress("")
//                .build();
//
//        log.info(String.format("Publish verify name screening with email: %s", corebankAmloEmail), loggingIndex.kv());
//        rabbitTemplate.convertAndSend(mqOpeningAccountConfiguration.emailVerifyNameScreeningQueue, "", message);
//    }
}
