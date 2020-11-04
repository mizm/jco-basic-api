# JCO BASIC API
SAP RFC(Remote Function Call) 호출 API

## IDEA
Intellij IDEA

## DATABASE
-

## 호출 스택 예시
POST
`/api/v1/sap/rfc`
accessInfo : 접속정보
functionName : call 할 function 명
importFields : import 탭에 내용
importChangings : changings 내용
importTables : table 내용 table은 [] list 로 표기


Request Example 1.
```json
{
    "accessInfo" : {
        "host" : "hostIP",
        "systemNumber" : "00",
        "clientNumber" : "400",
        "userId" : "userID",
        "userPw" : "USERPW",
        "language" : "KO",
        "type" : "3"
    },
    "functionName" : "ZSD_SND_DELIVERY_DOC", 
    "importFields" : {
         "I_WADAT" : "20200824",
         "I_BEROT" : "01",
         "I_LPRIO" : "24"
    }
}
```
Request Example 2.
```json
{
    "accessInfo" : {
    },
    "functionName" : "ZSD_RCV_DELIVERY_STATUS",
    "importTables" : {
        "IT_ITEM" : [
            {
                "VBELN" : "0080000228",
                "POSNR" : "000010",
                "STATUS" : "C"
            },
            {
                "VBELN" : "0080000229",
                "POSNR" : "000010",
                "STATUS" : "R"
            }
        ]
    }
}
```
Response
```json
{
    "exportChangings": {},
    "exportTables": {
        "IT_ITEM": [
            {
                "VBELN": "0080000228",
                "STATUS": "C",
                "POSNR": "000010"
            },
            {
                "VBELN": "0080000229",
                "STATUS": "R",
                "POSNR": "000010"
            }
        ]
    },
    "exportFields": {
        "E_TYPE": "S",
        "E_MSG": "성공적으로 조회하였습니다."
    }
}
```