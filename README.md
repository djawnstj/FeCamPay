## DB 설계
![fecam_pay_red](https://github.com/djawnstj/FeCamPay/assets/90193598/796a8112-b283-4c7b-8b7b-0d7dde56efac)

## 예상 문제

1. 같은 결제 건에 대해 중복되어 호출되어 액이 차감될 수 있습니다.

## 해결 방법

1. API 요청 시 멱등키를 이용해 해당 동일한 건에 대한 API 가 중복 호출되어도 서버에선 비지니스 로직이 한번만 실행되도록 처리합니다.

## API 흐름도
![페캠페이_api](https://github.com/djawnstj/FeCamPay/assets/90193598/9d1660e1-cb40-431a-8be3-9b2b7f858990)
