# KUPASS - back api spec

> KUPASS 백엔드 api 서버가 제공하는 api 스펙을 정리한 문서입니다.
>

# 회원가입

## 요청

| POST https://konkukstudy.site/api/signup   |
|--------------------------------------------|

## 요청 헤더

없음

## 요청 바디

| 필드명      | 설명             |
|----------|----------------|
| nickname | 회원 가입할 닉네임(id) |
| password | 회원 가입할 비밀번호    |

## 요청 예시

```json
{
    "nickname": "user1234",
    "password": "user1234"
}
```

## 응답

## 응답 바디

| 필드명     | 설명    | value                            |
|---------|-------|----------------------------------|
| success | 성공 여부 | success: 성공 <br/> fail: 실패       |
| message | 응답 설명 | signup ok <br/> conflict user id |

## 응답 예시

```json
{
    "success": "fail",
    "message": "conflict user id"
}
```

---

# 로그인

## 요청

| POST https://konkukstudy.site/api/authenticate   |
|--------------------------------------------------|

## 요청 헤더

없음

## 요청 바디

| 필드명      | 설명           |
|----------|--------------|
| nickname | 로그인할 닉네임(id) |
| password | 로그인할 비밀번호    |

## 요청 예시

```json
{
    "nickname": "user1234",
    "password": "user1234"
}
```

## 응답

## 응답 바디

| 필드명   | 설명            | value   |
|-------|---------------|---------|
| token | 로그인 인증 JWT 토큰 | 토큰값     |

## 응답 예시

```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMTIzNCIsImF1dGgiOiIiLCJleHAiOjE2NjY4NTI5MDZ9.99PsiuDLVQzuRDq3BxWDeVwoNLCOkTYSecvn-ibIPfs"
}
```

---

# 키워드 조회

## 요청

| GET https://konkukstudy.site/api/user/{nickname}/keywords   |
|-------------------------------------------------------------|

## 요청 헤더

```json
{
    "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMTIzNCIsImF1dGgiOiIiLCJleHAiOjE2NjY4NTI5MDZ9.99PsiuDLVQzuRDq3BxWDeVwoNLCOkTYSecvn-ibIPfs"
}
```

## 요청 파라미터

| 파라미터     | 필수 여부  | 설명             |
|----------|--------|----------------|
| nickname | Y      | 키워드 조회할 계정 아이디 |

## 요청 바디

없음

## 응답

## 응답 바디

| 필드명      | 설명         | value          |
|----------|------------|----------------|
| success  | 성공 여부      | success: 응답 완료 |
| message  | 응답 설명      | ok             |
| keywords | 관심 키워드 리스트 | []             |

## 응답 예시

```json
{
    "success": "success",
    "message": "ok",
    "keywords": [
        "비트코인",
        "전쟁",
        "러시아"
    ]
}
```

---

# 키워드 저장

## 요청

| PUT https://konkukstudy.site/api/user/{nickname}/keywords/{keyword} |
|---------------------------------------------------------------------|

## 요청 헤더

```json
{
    "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMTIzNCIsImF1dGgiOiIiLCJleHAiOjE2NjY4NTI5MDZ9.99PsiuDLVQzuRDq3BxWDeVwoNLCOkTYSecvn-ibIPfs"
}
```

## 요청 파라미터

| 파라미터     | 필수 여부 | 설명             |
|----------|-------|----------------|
| nickname | Y     | 키워드 저장할 계정 아이디 |
| keyword  | Y     | 새로 저장할 키워드     |

## 요청 바디

없음

## 응답

## 응답 바디

| 필드명     | 설명    | value                      |
|---------|-------|----------------------------|
| success | 성공 여부 | success: 성공 <br/> fail: 실패 |
| message | 응답 설명 | keyword already exists     |

## 응답 예시

```json
{
    "success": "fail",
    "message": "keyword already exists"
}
```

---

# 키워드 삭제

## 요청

| DELETE https://konkukstudy.site/api/user/{nickname}/keywords/{keyword} |
|------------------------------------------------------------------------|

## 요청 헤더

```json
{
  "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMTIzNCIsImF1dGgiOiIiLCJleHAiOjE2NjY4NTI5MDZ9.99PsiuDLVQzuRDq3BxWDeVwoNLCOkTYSecvn-ibIPfs"
}
```

## 요청 파라미터

| 파라미터     | 필수 여부   | 설명             |
|----------|---------|----------------|
| nickname | Y       | 키워드 저장할 계정 아이디 |
| keyword  | Y       | 새로 저장할 키워드     |

## 요청 바디

없음

## 응답

## 응답 바디

| 필드명     | 설명    | value                                                     |
|---------|-------|-----------------------------------------------------------|
| success | 성공 여부 | success: 성공 <br/> fail: 실패                                |
| message | 응답 설명 | successfully deleted keyword <br/> keyword already exists |

## 응답 예시

```json
{
    "success": "success",
    "message": "successfully deleted 전쟁"
}
```

---

# 기사 검색

## 요청

| GET https://konkukstudy.site/api/search/article?publisher={신문사}&category={카테고리}&keyword={키워드}&page={페이지 번호} |
|-------------------------------------------------------------------------------------------------------------|

## 요청 헤더

```json
{
    "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMTIzNCIsImF1dGgiOiIiLCJleHAiOjE2NjY4NTI5MDZ9.99PsiuDLVQzuRDq3BxWDeVwoNLCOkTYSecvn-ibIPfs"
}
```

## 요청 파라미터

| 파라미터      | 필수 여부   | 설명                                                   |
|-----------|---------|------------------------------------------------------|
| publisher | N       | 검색할 신문사 <br/> e.x. 조선일보, 동아일보                        |
| category  | N       | 검색할 카테고리 <br/> e,x. 정치, 경제, 사회, 생활문화, IT과학, 세계, 오피니언 |
| keyword   | N       | 검색할 키워드 <br/> e.x. 전쟁, 범죄, 비트코인                      |
| page      | N       | 페이지 번호 <br/> e.x. 0, 1, 2 …                          |

## 요청 바디

없음

## 응답

## 응답 바디

| 필드명      | 설명          | value                                 |
|----------|-------------|---------------------------------------|
| success  | 성공 여부       | success                               |
| message  | 응답 설명       | ok                                    |
| articles | 필터링된 기사 리스트 | []                                    |
| last     | 마지막 페이지 여부  | true: 마지막 페이지 <br/> false: 마지막 페이지 아님 |

## 응답 예시

```json
{
  "success": "success",
  "message": "ok",
  "articles": [
    {
      "articleId": 219,
      "createDate": "2022-11-04T00:24:33",
      "publisher": "뉴시스",
      "category": "세계",
      "source": "https://n.news.naver.com/mnews/article/003/0011516466?sid=104",
      "title": "IAEA 우크라 내 더티밤 발견 못해…핵 활동 징후 없어",
      "content": "키이우 AP 뉴시스 라파엘 그로시 오른쪽 국제원자력기구 IAEA 사무총장과 볼로디미르 젤렌스키 우크라이나 대통령. 2022.10.07. 서울 뉴시스 박준호 기자 국제원자력기구 IAEA 가 우크라이나가 재래식 무기에 방사능 물질을 섞은 더티밤 dirty boms 을 생산하고 있다는 러시아의 주장에 대한 검증에 나섰지만 관련 정황은 없는 것으로 나타났다. IAEA는 3일 현지시간 더티밤 에 대한 작업이 이루어지고 있다는 러시아의 주장에 대해 우크라이나의 요청으로 사찰한 우크라이나 내 세 곳에서 신고되지 않은 핵 활동의 징후를 발견하지 못했다고 발표했다. IAEA는 지금까지의 결과에 대한 우리의 기술적 과학적 평가는 이들 세 곳에서 신고되지 않은 핵 활동과 물질의 징후를 보여주지 않았다 며 채취한 환경 샘플이 분석될 것이라고 보도했다. 지난 주 블라디미르 푸틴 러시아 대통령은 러시아는 소위 더티밤 이 준비되고 있는 사건에 대해 알고 있다 면서 러시아는 일반적으로 어디에서 준비되고 있는지 알고 있다고 말했다. 푸틴은 토치카 U 또는 다른 전술 미사일에 탑재되어 폭발한 후 러시아를 비난할 가능성을 포함한 음모론에 대한 증거는 제시하지 않았다.",
      "summary": "2022.10.07. 서울 뉴시스 박준호 기자 국제원자력기구 IAEA 가 우크라이나가 재래식 무기에 방사능 물질을 섞은 더티밤 dirty boms 을 생산하고 있다는 러시아의 주장에 대한 검증에 나섰지만 관련 정황은 없는 것으로 나타났다IAEA는 3일 현지시간 더티밤 에 대한 작업이 이루어지고 있다는 러시아의 주장에 대해 우크라이나의 요청으로 사찰한 우크라이나 내 세 곳에서 신고되지 않은 핵 활동의 징후를 발견하지 못했다고 발표했다IAEA는 지금까지의 결과에 대한 우리의 기술적 과학적 평가는 이들 세 곳에서 신고되지 않은 핵 활동과 물질의 징후를 보여주지 않았다 며 채취한 환경 샘플이 분석될 것이라고 보도했다",
      "keywords": [
        "푸틴",
        "미사일",
        "원자력",
        "우크라이나",
        "러시아"
      ]
    }
  ],
  "last": "true"
}
```