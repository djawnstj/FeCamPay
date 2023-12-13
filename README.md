## DB 설계

![fecam_pay_red.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/8004d4da-91f8-433e-b956-1a8329fe766e/8d44041e-a7f8-49ed-bf51-a6fb517c2353/fecam_pay_red.png)

## 예상 문제

1. 같은 결제 건에 대해 중복되어 호출되어 액이 차감될 수 있습니다.

## 해결 방법

1. API 요청 시 멱등키를 이용해 해당 동일한 건에 대한 API 가 중복 호출되어도 서버에선 비지니스 로직이 한번만 실행되도록 처리합니다.

## API 흐름도

![페캠페이_api.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/8004d4da-91f8-433e-b956-1a8329fe766e/f0d1a2db-38e1-44e4-89b8-c23bf4269642/%E1%84%91%E1%85%A6%E1%84%8F%E1%85%A2%E1%86%B7%E1%84%91%E1%85%A6%E1%84%8B%E1%85%B5_api.png)