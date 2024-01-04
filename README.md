# 즐거운 설문 프로젝트

 # skill
 - Spring Boot
 - Spring Security
 - Mysql
 - MyBatis
 - Lombok
 - Java jwt
 - swagger
   
<br/>

# 프로젝트 내용
### 모두 가볍고 즐겁게 사용자들이 서로의 의견을 주고받을 수 있고 Enjoy&Easy(E&E) 설문을 등록하여 다른 사용자들이 참가하여 즐길수 있는 사이트입니다.

<br/>

# 구현 기능

<br/>

## E&E Survey
- 등록 (O)
- 사진 업로드 (0)
- 사진 출력
- 검색 (O)
- 추천
- 참여 (O)
- 디테일 조회 (O)

<br/>

## 게시판
- 등록 (O)
- 파일 업로드
- 파일 출력
- 검색 (O)
- 댓글 : 생성, 수정, 삭제, 리스트 출력 (O)
- pagenation (O)
- 상세 (O)

<br/>

## 문의
- 1대1 문의 : 질문 등록, 답변 등록, 1대1 문의 출력, 리스트 출력 (O)
- Q&A 문의 : Q&A 문의 Q 등록, Q&A 문의 A 등록, Q&A 문의 출력, Q&A 문의 리스트 출력

<br/>

## 관리자
- 유저(사용자 계정) 관리
- 문의 관리
- 게시글 관리
- 댓글 관리
- 공지사항 등록
- E&E survey 관리

<br/>

# API (진행중)

# 관리자

## 설문

---

- `**[GET]` 특정 유저가 참여한 설문 리스트 (관리자)**
    
    
    | Description | 관리자의 유저관리 : 유저 선택시에 해당 유저가 참여한 설문에 대한 리스트 출력 API |
    | --- | --- |
    | URL | /admin/user/attend/survey/list |
    | Auth Required |  |
    
    | Paramater | Type  | Required | Description | Default |
    | --- | --- | --- | --- | --- |
    | body params |  |  |  |  |
    |  |  |  |  |  |
    | url params |  |  |  |  |
    | search | string | no | 설문 제목 검색 | “” |
    | userId | int | yes | 선택한 유저 아이디 |  |
    | page | int | no | 페이징 처리를 위한 페이지 번호 : 페이지당 10개 출력 | 1 |
    - **🧑🏻‍💻 Header Example**
        
        ```jsx
        { 
        	header : { 
        		Authorization : Json Web Token 
        		} 
        }
        ```
        
    - **✅ Response 200**
        
        ```jsx
        {
        	"atdSvListCount" : 34,
        	"atdSurveyList" : 
        	[
        		{
        			id : 1, 
        			topic_id : 2, 
        			survey_content : "카페에서 머먹는게 맛있어?", 
        			member_id : 1, 
        			start_date: "2023-12-31", 
        			end_date : "2024-01-01", 
        			hit : 1, 
        			delete_state : false, 
        			alarm_state : false
        		},
        		{
        			id : 2, 
        			topic_id : 2, 
        			survey_content : "카페에서 머먹는게 맛있어?", 
        			member_id : 1, 
        			start_date: "2023-12-31", 
        			end_date : "2024-01-01", 
        			hit : 1, 
        			delete_state : false, 
        			alarm_state : false
        		},
        		{
        			id : 3, 
        			topic_id : 2, 
        			survey_content : "카페에서 머먹는게 맛있어?", 
        			member_id : 1, 
        			start_date: "2023-12-31", 
        			end_date : "2024-01-01", 
        			hit : 1, 
        			delete_state : false, 
        			alarm_state : false
        		},...
        	]
        }
        ```
        
    - **✅ Response 404**
        
        ```jsx
        {error: "error message"}
        ```
        

---

- `**[GET]` 특정 유저가 작성한 설문 리스트 (관리자)**
    
    
    | Description | 관리자의 유저관리 : 유저 선택시에 해당 유저가 작성한 설문에 대한 리스트 출력 API |
    | --- | --- |
    | URL | /admin/user/survey/list |
    | Auth Required | Yes |
    
    | Paramater | Type  | Required | Description | Default |
    | --- | --- | --- | --- | --- |
    | body params |  |  |  |  |
    |  |  |  |  |  |
    | url params |  |  |  |  |
    | search | string | no | 설문 제목 검색 | "” |
    | userId | int | yes | 선택한 유저 아이디 |  |
    | page | int | no | 페이징 처리를 위한 페이지 번호 : 페이지당 10개 출력 | 1 |
    | order | string | no | order | 최신 순서 |
    - **🧑🏻‍💻 Header Example**
        
        ```jsx
        { 
        	header : { 
        		Authorization : Json Web Token 
        		} 
        }
        ```
        
    - **✅ Response 200**
        
        ```jsx
        {
        	"count" : 34,
        	"surveyList" : 
        	[
        		{
        			id : 1, 
        			topic_id : 2, 
        			survey_content : "카페에서 머먹는게 맛있어?", 
        			member_id : 1, 
        			start_date: "2023-12-31", 
        			end_date : "2024-01-01", 
        			hit : 1, 
        			delete_state : false, 
        			alarm_state : false
        		},
        		{
        			id : 2, 
        			topic_id : 2, 
        			survey_content : "카페에서 머먹는게 맛있어?", 
        			member_id : 1, 
        			start_date: "2023-12-31", 
        			end_date : "2024-01-01", 
        			hit : 1, 
        			delete_state : false, 
        			alarm_state : false
        		},
        		{
        			id : 3, 
        			topic_id : 2, 
        			survey_content : "카페에서 머먹는게 맛있어?", 
        			member_id : 1, 
        			start_date: "2023-12-31", 
        			end_date : "2024-01-01", 
        			hit : 1, 
        			delete_state : false, 
        			alarm_state : false
        		},...
        	]
        }
        ```
        
    - **✅ Response 404**
        
        ```jsx
        {error: "error message"}
        ```
        

---

- `**[GET]` 전체 설문 리스트 (관리자)**
    
    
    | Description | 관리자 권한 : 비활성화 되어있는 설문을 포함하여 리스트 출력 API |
    | --- | --- |
    | URL | /admin/survey/list |
    | Auth Required | Yes |
    
    | Paramater | Type  | Required | Description | Default |
    | --- | --- | --- | --- | --- |
    | body params |  |  |  |  |
    |  |  |  |  |  |
    | url params |  |  |  |  |
    | page | int | no | 페이징 처리를 위한 페이지 번호 : 페이지당 10개 출력 | 1 |
    | order | string | no | order | 최신 순서 |
    | search | string | no | 설문 제목 검색 | "” |
    - **🧑🏻‍💻 Header Example**
        
        ```jsx
        { 
        	header : { 
        		Authorization : Json Web Token 
        		} 
        }
        ```
        
    - **✅ Response 200**
        
        ```jsx
        {
        	"surveyListCount" : 34,
        	"surveyList" : 
        	[
        		{
        			id : 1, 
        			topic_id : 2, 
        			survey_content : "카페에서 머먹는게 맛있어?", 
        			member_id : 1, 
        			start_date: "2023-12-31", 
        			end_date : "2024-01-01", 
        			hit : 1, 
        			delete_state : false, 
        			alarm_state : false
        		},
        		{
        			id : 2, 
        			topic_id : 2, 
        			survey_content : "카페에서 머먹는게 맛있어?", 
        			member_id : 1, 
        			start_date: "2023-12-31", 
        			end_date : "2024-01-01", 
        			hit : 1, 
        			delete_state : false, 
        			alarm_state : false
        		},
        		{
        			id : 3, 
        			topic_id : 2, 
        			survey_content : "카페에서 머먹는게 맛있어?", 
        			member_id : 1, 
        			start_date: "2023-12-31", 
        			end_date : "2024-01-01", 
        			hit : 1, 
        			delete_state : false, 
        			alarm_state : false
        		},...
        	]
        }
        ```
        
    - **✅ Response 404**
        
        ```jsx
        {}
        ```
        

---

- `**[GET]` 전체 설문 디테일 : 상시 마감 후 Data (관리자)**
    
    
    | Description | 관리자 권한 : 비활성화 되어있는 설문을 포함하고 참여 를 가정하지 않는 디테일 출력 API |
    | --- | --- |
    | URL | /admin/survey/detail |
    | Auth Required | Yes |
    
    | Paramater | Type  | Required | Description | Description |
    | --- | --- | --- | --- | --- |
    | body params |  |  |  |  |
    |  |  |  |  |  |
    | url params |  |  |  |  |
    | surveyId | int | yes |  |  |
    - **🧑🏻‍💻 Header Example**
        
        ```jsx
        { 
        	header : { 
        		Authorization : Json Web Token 
        		} 
        }
        ```
        
    - **✅ Response 200**
        
        ```jsx
        {
        	surveyDetail : {
        			id : 1, 
        			topic_id : 2, 
        			survey_content : "카페에서 머먹는게 맛있어?", 
        			member_id : 1, 
        			start_date: "2023-12-31", 
        			end_date : "2024-01-01", 
        			hit : 1, 
        			delete_state : false, 
        			alarm_state : false
        	},
        	questionCount : [
        		{
        			question : "질문에 대한 선택지 1",
        			survey_id : 1,
        			participation_count : 123
        		},
        		{
        			question : "질문에 대한 선택지 2",
        			survey_id : 1,
        			participation_count : 29
        		},
        		{
        			question : "질문에 대한 선택지 3",
        			survey_id : 1,
        			participation_count : 34
        		},
        	],
        	그래프_통계_데이터1 : {},
        	그래프_통계_데이터2 : {}
        }
        ```
        
    - **✅ Response 404**
        
        ```jsx
        {}
        ```
        

---

- `**[DELETE | POST]` 설문 삭제 (관리자)**
    
    
    | Description | 관리자 권한 : 모든 선택한 단일 설문을 삭제 할 수 있는 API |
    | --- | --- |
    | URL | /admin/survey/delete |
    | Auth Required | Yes |
    
    | Paramater | Type  | Required | Description | Default |
    | --- | --- | --- | --- | --- |
    | body params |  |  |  |  |
    | surveyId | int | Yes | 삭제할 설문 ID |  |
    | url params |  |  |  |  |
    |  |  |  |  |  |
    - **🧑🏻‍💻 Header Example**
        
        ```jsx
        { 
        	header : { 
        		Authorization : Json Web Token 
        		} 
        }
        ```
        
    - **✅ Response 200**
        
        ```jsx
        {message : "설문 삭제를 완료하였습니다"}
        ```
        
    - **✅ Response 404**
        
        ```jsx
        {}
        ```
        

---

- `**[DELETE | POST]` 설문 리스트 삭제 (관리자)**
    
    
    | Description | 관리자 권한 : 모든 선택한 설문 리스트를 삭제 할 수 있는 API |
    | --- | --- |
    | URL | /admin/survey/delete/list |
    | Auth Required | Yes |
    
    | Paramater | Type  | Required | Description | Default |
    | --- | --- | --- | --- | --- |
    | body params |  |  |  |  |
    | surveyIds | List<Integer> (Array) | yes | 삭제할 설문 ID 리스트 |  |
    | url params |  |  |  |  |
    |  |  |  |  |  |
    - **🧑🏻‍💻 Header Example**
        
        ```jsx
        { 
        	header : { 
        		Authorization : Json Web Token 
        		} 
        }
        ```
        
    - **✅ Response 200**
        
        ```jsx
        {message : "삭제가 완료되었습니다"}
        ```
        
    - **✅ Response 404**
        
        ```jsx
        {}
        ```
        

---

## 회원

---

- `**[GET]` 회원 디테일 (관리자)**
    
    
    | Description | 유저 디테일 정보 출력 (비활성화 되어있는 유저 포함) 관리자 Ver API |
    | --- | --- |
    | URL | /admin/user/detail |
    | Auth Required | Yes |
    
    | Paramater | Type  | Required | Description | Default |
    | --- | --- | --- | --- | --- |
    | url params |  |  |  |  |
    | userId | int | yes | 출력할 유저 PK ID |  |
    - **🧑🏻‍💻 Header Example ( Authorization )**
        
        ```jsx
        { 
        	header : { 
        		Authorization : Json Web Token 
        		} 
        }
        ```
        
    - **✅ Response 200**
        
        ```jsx
        {}
        ```
        
    - **✅ Response 404**
        
        ```jsx
        {}
        ```
        

---

- `**[GET]` 회원 리스트 (관리자)**
    
    
    | Description | 회원들 리스트 출력 API |
    | --- | --- |
    | URL | /admin/user/list |
    | Auth Required | Yes |
    
    | Paramater | Type  | Required | Description | Default |
    | --- | --- | --- | --- | --- |
    | body params |  |  |  |  |
    |  |  |  |  |  |
    | url params |  |  |  |  |
    | search | string | no | 유저 로그인시 아이디 검색 정보 | “” |
    | page | int | no | 페이지 | 1 |
    - **🧑🏻‍💻 Header Example**
        
        ```jsx
        header : { 
        		Authorization : Json Web Token
        } 
        
        ```
        
    - **✅ Response 200**
        
        ```jsx
        {}
        ```
        
    - **✅ Response 404**
        
        ```jsx
        {}
        ```
        

---

- `**[MODIFY | POST]` 회원 정보 수정 (관리자)**
    
    
    | Description | 회원가입 되어있는 유저에 대해서 회원 정보를 수정할 수 있는 관리자 version API |
    | --- | --- |
    | URL | /admin/modify/user_info |
    | Auth Required | Yes |
    
    | Paramater | Type  | Required | Description | Default |
    | --- | --- | --- | --- | --- |
    | body params |  |  |  |  |
    | username | string | yes | 유저 ID ( 사용자 정보 변경을 위해 필요 ) |  |
    | name | string | no | 변경할 유저 이름 | Usre Already Name |
    | email | string | no | 변경할 유저 이메일 | Usre Already Email |
    | birth | string | no | 변경할 유저 생일 | 990310 | Usre Already Birth |
    | gender | string | no | 변경할 유저 성별 | 1 | User Already Gender |
    | url params |  |  |  |  |
    |  |  |  |  |  |
    - **🧑🏻‍💻 Header Example**
        
        ```jsx
        { 
        	header : { 
        		Authorization : Json Web Token 
        		} 
        }
        ```
        
    - **✅ Response 200**
        
        ```jsx
        {}
        ```
        
    - **✅ Response 404**
        
        ```jsx
        {}
        ```
        

---

- `**[DELETE | POST]` 회원 단일 삭제 (관리자)**
    
    
    | Description | 선택한 회원 단일 삭제 (비활성화) 관리자 Ver API |
    | --- | --- |
    | URL | /admin/user/delete |
    | Auth Required | Yes |
    
    | Paramater | Type  | Required | Description | Default |
    | --- | --- | --- | --- | --- |
    | body params |  |  |  |  |
    | userId | int | Yes | 삭제 (비활성화) 할 유저의 ID (해당 아이디는 테이블 상의 아이디인 PK) |  |
    | url params |  |  |  |  |
    |  |  |  |  |  |
    - **🧑🏻‍💻 Header Example ( Authorization )**
        
        ```jsx
        { 
        	header : { 
        		Authorization : Json Web Token 
        		} 
        }
        ```
        
    - **✅ Response 200**
        
        ```jsx
        {}
        ```
        
    - **✅ Response 404**
        
        ```jsx
        {}
        ```
        

---

- `**[DELETE | POST]` 회원 리스트 삭제 (관리자)**
    
    
    | Description | 선택한 회원들을 삭제 ( 비활성화 ) 할 수 있는 API |
    | --- | --- |
    | URL | /admin/user/delete/list |
    | Auth Required | Yes |
    
    | Paramater | Type  | Required | Description | Default |
    | --- | --- | --- | --- | --- |
    | body params |  |  |  |  |
    | userIds | List<Integer> | Yes | 삭제 (비활성화) 할 유저의 ID (해당 아이디는 테이블 상의 아이디인 PK) |  |
    | url params |  |  |  |  |
    |  |  |  |  |  |
    - **🧑🏻‍💻 Header Example ( Authorization )**
        
        ```jsx
        { 
        	header : { 
        		Authorization : Json Web Token 
        		} 
        }
        ```
        
    - **✅ Response 200**
        
        ```jsx
        {}
        ```
        
    - **✅ Response 404**
        
        ```jsx
        {}
        ```
        

---

## 게시판

## 댓글

---

- `**[DELETE | POST]` 댓글 삭제 (관리자)**
    
    
    | Description | 댓글 삭제 관리자 버전 API |
    | --- | --- |
    | URL | /admin/comment/delete |
    | Auth Required | Yes |
    
    | Paramater | Type  | Required | Description | Default |
    | --- | --- | --- | --- | --- |
    | body params |  |  |  |  |
    | cmtId | Integer | Yes | 삭제할 댓글 ID(PK) |  |
    | url params |  |  |  |  |
    |  |  |  |  |  |
    - **🧑🏻‍💻 Header Example ( Authorization )**
        
        ```jsx
         
        header : { 
        		Authorization : Json Web Token 
        } 
        ```
        
    - **✅ Response 200**
        
        ```jsx
        {}
        ```
        
    - **✅ Response 404**
        
        ```jsx
        {}
        ```
        

---

- `**[DELETE | POST]` 댓글 리스트 삭제 (관리자)**
    
    
    | Description | 댓글 리스트 삭제 관리자 버전 API |
    | --- | --- |
    | URL | /admin/comment/list/delete |
    | Auth Required | Yes |
    
    | Paramater | Type  | Required | Description | Default |
    | --- | --- | --- | --- | --- |
    | body params |  |  |  |  |
    | cmtIds | List<Integer> | Yes | 삭제할 댓글 ID(PK) List |  |
    | url params |  |  |  |  |
    |  |  |  |  |  |
    - **🧑🏻‍💻 Header Example ( Authorization )**
        
        ```jsx
         
        header : { 
        		Authorization : Json Web Token 
        } 
        ```
        
    - **✅ Response 200**
        
        ```jsx
        {}
        ```
        
    - **✅ Response 404**
        
        ```jsx
        {}
        ```
        

---

## 파일

## 1대1 문의

## QnA

# 일반 회원

## 설문

---

- `**[GET]` 작성한 설문 리스트 (일반 회원)**
    
    
    | Description | 로그인한 유저의 마이페이지 이동시 자신이 작성한 설문조사에 대한 리스트가 출력 하기위해 사용 |
    | --- | --- |
    | URL | /user/survey/list/forUser |
    | Auth Required | Yes |
    
    | Paramater | Type  | Required | Description | Default |
    | --- | --- | --- | --- | --- |
    | body params |  |  |  |  |
    |  |  |  |  |  |
    | url params |  |  |  |  |
    | page | int | No | 작성한(= 생성한) 설문의 출력할 페이지 번호, 한 페이지당 10개 데이터 출력 | 1 |
    | search | string | No | 작성한 설문중 찾기 검색어 | “” |
    | order | string | No | 작성한 설문 리스트의 Order 조회 | 최신 순서 |
    - **🧑🏻‍💻 Header Example**
        
        ```jsx
        { 
        	header : { 
        		Authorization : Json Web Token 
        		} 
        }
        ```
        
    - **✅ Response 200**
        
        ```jsx
        {
        	"survey_list" : [
        		{
        		"id" : 4,
        		"topic_id" : 2,
        		"survey_content" : "카페에 가면 무슨 커피를 드시나요?",
        		"member_id" : 3,
        		"start_date" : 2023-12-31 12:00:00,
        		"end_date" : 2024-01-12 12:00:00,
        		"hit" : 3,
        		"delete_state" : false,
        		"alarm_state" : false
        		},
        		{
        		"id" : 3,
        		"topic_id" : 2,
        		"survey_content" : "동물원에 가면 어떤 동물이 제일 좋아?",
        		"member_id" : 3,
        		"start_date" : 2023-12-31 12:00:00,
        		"end_date" : 2024-01-12 12:00:00,
        		"hit" : 3,
        		"delete_state" : false,
        		"alarm_state" : false
        		},
        		{
        		"id" : 2,
        		"topic_id" : 2,
        		"survey_content" : "졸리면 어떻게 잠을 깨?",
        		"member_id" : 3,
        		"start_date" : 2023-12-31 12:00:00,
        		"end_date" : 2024-01-12 12:00:00,
        		"hit" : 3,
        		"delete_state" : false,
        		"alarm_state" : false
        		},
        		{
        		"id" : 1,
        		"topic_id" : 2,
        		"survey_content" : "겨울에 잠깐 나갈때 따뜻하게 입고나가?",
        		"member_id" : 3,
        		"start_date" : 2023-12-31 12:00:00,
        		"end_date" : 2024-01-12 12:00:00,
        		"hit" : 3,
        		"delete_state" : false,
        		"alarm_state" : false
        		}
        	],
        	"count" : 4
        }
        ```
        
    - **✅ Response 404**
        
        ```jsx
        {message : "no message"}
        ```
        

---

- `**[GET]` 참여한 설문 리스트 (일반 회원)**
    
    
    | Description | 로그인한 유저의 마이페이지 이동시 자신이 참여한 설문조사에 대한 리스트가 출력 된다. |
    | --- | --- |
    | URL | /user/survey/attend/list/forUser |
    | Auth Required | Yes |
    
    | Paramater | Type  | Required | Description | Default |
    | --- | --- | --- | --- | --- |
    | body params |  |  |  |  |
    |  |  |  |  |  |
    | url params |  |  |  |  |
    | search | string | no | 작성한 설문중 찾기 검색어 | “” |
    | page | int | no | 작성한(= 생성한) 설문의 출력할 페이지 번호, 한 페이지당 10개 데이터 출력 | 1 |
    - **🧑🏻‍💻 Header Example**
        
        ```jsx
        { 
        	header : { 
        		Authorization : Json Web Token 
        		} 
        }
        ```
        
    - **✅ Response 200**
        
        ```jsx
        {
        	"surveyAttendCount" : 4,
        	"surveyAttendListForUser" : [
        		{
        		"id" : 4,
        		"topic_id" : 2,
        		"survey_content" : "카페에 가면 무슨 커피를 드시나요?",
        		"member_id" : 3,
        		"start_date" : 2023-12-31 12:00:00,
        		"end_date" : 2024-01-12 12:00:00,
        		"hit" : 3,
        		"delete_state" : false,
        		"alarm_state" : false
        		},
        		{
        		"id" : 3,
        		"topic_id" : 2,
        		"survey_content" : "동물원에 가면 어떤 동물이 제일 좋아?",
        		"member_id" : 3,
        		"start_date" : 2023-12-31 12:00:00,
        		"end_date" : 2024-01-12 12:00:00,
        		"hit" : 3,
        		"delete_state" : false,
        		"alarm_state" : false
        		},
        		{
        		"id" : 2,
        		"topic_id" : 2,
        		"survey_content" : "졸리면 어떻게 잠을 깨?",
        		"member_id" : 3,
        		"start_date" : 2023-12-31 12:00:00,
        		"end_date" : 2024-01-12 12:00:00,
        		"hit" : 3,
        		"delete_state" : false,
        		"alarm_state" : false
        		},
        		{
        		"id" : 1,
        		"topic_id" : 2,
        		"survey_content" : "겨울에 잠깐 나갈때 따뜻하게 입고나가?",
        		"member_id" : 3,
        		"start_date" : 2023-12-31 12:00:00,
        		"end_date" : 2024-01-12 12:00:00,
        		"hit" : 3,
        		"delete_state" : false,
        		"alarm_state" : false
        		}
        	]
        }
        ```
        
    - **✅ Response 400**
        
        ```jsx
        예시 코드 수정 필요
        // NOT_FOUND_ID
        {
          "NOT_FOUND_ID": "ID는 2가 존재하지 않습니다."
        }
        ```
        

---

- `**[GET]` 설문 리스트 (일반 회원)**
    
    
    | Description | 설문 리스트 출력 API : 일반 회원, 전체 |
    | --- | --- |
    | URL | /survey/list |
    | Auth Required | No |
    
    | Paramater | Type  | Required | Description | Default |
    | --- | --- | --- | --- | --- |
    | url params |  |  |  |  |
    | page | int | no | 페이지 번호 | 1 |
    | search | string | no | 검색 내용 | “” |
    | order | string | no | Ordering | “최신 순서” |
    | body params |  |  |  | ` |
    |  |  |  |  |  |
    - **✅ Response 200**
        
        ```jsx
        {
        "surveyList" : [
        	{
        		"id" : 1,
        		"survey_content" : "회원가입 할때 어떤걸 먼저 작성하나요?",
        		"end_date" : "2023-12-25",
        		"start_date" : "2023-12-21"
        	},
        ],
        "surveyListAllCount" : 12,
        }
        ```
        
    - **✅ Response 500**
        
        ```jsx
        { message: "리스트 출력에 실패하였습니다." }
        ```
        
    

---

- `**[POST]` 설문 작성 (일반 회원)**
    
    
    | Description | 로그이한 유저의 설문 생성 API |
    | --- | --- |
    | URL | /user/survey/reg |
    | Auth Required | Yes |
    
    | Paramater | Type  | Required | Place | Description | Example |
    | --- | --- | --- | --- | --- | --- |
    | form data |  |  |  |  |  |
    | topic_id | int (text) | Yes | body | 주제 아이디 | 1 |
    | survey_content  | string (text) | Yes | body | 설문 내용 | "빵보다 밥이 좋나요?” |
    | end_date  | string (text) | Yes | body | 설문 종료 시간 | “2024-01-02” |
    | questions file | MultipartFile (file) | No | body | 설문 사진 | me.png |
    | questions | List<String> (text) | Yes | body | 설문 질문들 | [”네”, ”아니요”, ”둘다 좋아요”] |
    | url params |  |  |  |  |  |
    |  |  |  |  |  |  |
    - **🧑🏻‍💻 Header Example**
        
        ```jsx
        { 
        	header : { 
        		Authorization : Json Web Token 
        		} 
        }
        ```
        
    - **✅ Response 200**
        
        ```jsx
        {
        	message : "complete_save_survey";
        }
        ```
        
    - **✅ Response 404**
        
        ```jsx
        {}
        ```
        

---

- `**[GET]` 설문 디테일 : 마감 전 (일반 회원)**
    
    
    | Description | 설문 Detail 출력 API : 일반 회원, 상세,  |
    | --- | --- |
    | URL | /user/survey/detail |
    | Auth Required | Yes |
    
    | Paramater | Type  | Required | Description | Default |
    | --- | --- | --- | --- | --- |
    | url params |  |  |  |  |
    | survey_id | int | yes | 설문 ID |  |
    | body params |  |  |  | ` |
    |  |  |  |  |  |
    - **🧑🏻‍💻 Header Example**
        
        ```jsx
        { 
        	header : { 
        		Authorization : Json Web Token 
        		} 
        }
        ```
        
    - **✅ Response 200**
        
        ```jsx
        {
        	"surveyDetail" : 
        		{
        			"id" : 1,
        			"survey_content" : "회원가입 할때 어떤걸 먼저 작성하나요?",
        			"end_date" : "2023-12-25",
        			"start_date" : "2023-12-21"
        		},
        	"surveyPicture" : 
        		{
        			"id" : 1,
        			"orgNm" : "testFile",
        			"saveNm" : "eSeww23!2$fdsEfeiSlidkj",
        			"savePath" : "/file/",
        			"survey_id" : 1
        		},
        	"surveyOptions" : 
        		[
        			{
        				"id" : 1,
        				"option" : "이름 먼저 작성해요",
        			},
        			{
        				"id" : 2,
        				"option" : "패스워드 먼저 작성해요",
        			},
        			{
        				"id" : 3,
        				"option" : "이메일 먼저 작성해요",
        			},
        			{
        				"id" : 4,
        				"option" : "그떄그때 달라요",
        			},
        		]
        }
        ```
        
    - **✅ Response 500**
        
        ```jsx
        { message: "설문 상세 출력에 실패하였습니다." }
        ```
        
    

---

- `**[GET]` 설문 디테일 : 마감 후 (일반 회원)**
    
    
    | Description | 설문 리스트 출력 API : 일반 회원, 전체 |
    | --- | --- |
    | URL | /user/survey/afterClosing/detail |
    | Auth Required | Yes |
    
    | Paramater | Type  | Required | Description | Default |
    | --- | --- | --- | --- | --- |
    | url params |  |  |  |  |
    | survey_id | int | yes | 설문 아이디 |  |
    | body params |  |  |  | ` |
    |  |  |  |  |  |
    - **🧑🏻‍💻 Header Example**
        
        ```jsx
        { 
        	header : { 
        		Authorization : Json Web Token 
        		} 
        }
        ```
        
    - **✅ Response 200**
        
        ```jsx
        {
        	surveyDetail : {
        			id : 1, 
        			topic_id : 2, 
        			survey_content : "카페에서 머먹는게 맛있어?", 
        			member_id : 1, 
        			start_date: "2023-12-31", 
        			end_date : "2024-01-01", 
        			hit : 1, 
        			delete_state : false, 
        			alarm_state : false
        	},
        	questionCount : [
        		{
        			question : "질문에 대한 선택지 1",
        			survey_id : 1,
        			participation_count : 123
        		},
        		{
        			question : "질문에 대한 선택지 2",
        			survey_id : 1,
        			participation_count : 29
        		},
        		{
        			question : "질문에 대한 선택지 3",
        			survey_id : 1,
        			participation_count : 34
        		},
        	],
        	그래프_통계_데이터1 : {},
        	그래프_통계_데이터2 : {}
        }
        ```
        
    - **✅ Response 500**
        
        ```jsx
        { message: "리스트 출력에 실패하였습니다." }
        ```
        
    

---

- `**[DELETE | POST]` 작성한 설문 삭제 (일반 회원)**
    
    
    | Description | 일반 (로그인한 회원) : 본인이 생성한 설문 삭제 API |
    | --- | --- |
    | URL | /user/survey/delete |
    | Auth Required | Yes |
    
    | Paramater | Type  | Required | Description | Default |
    | --- | --- | --- | --- | --- |
    | body params |  |  |  |  |
    | surveyId | int | yes | 삭제할 설문 ID |  |
    | url params |  |  |  |  |
    |  |  |  |  |  |
    - **🧑🏻‍💻 Header Example**
        
        ```jsx
        { 
        	header : { 
        		Authorization : Json Web Token 
        		} 
        }
        ```
        
    - **✅ Response 200**
        
        ```jsx
        {message : "설문 삭제를 완료하였습니다"}
        ```
        
    - **✅ Response 404**
        
        ```jsx
        {}
        ```
        

---

## 회원

---

- `**[POST]` 로그인**
    
    
    | Description | 로그인 API |
    | --- | --- |
    | URL | /login |
    | Auth Required | No |
    
    | Paramater | Type  | Required | Description | Default |
    | --- | --- | --- | --- | --- |
    | url params |  |  |  |  |
    |  |  |  |  |  |
    | body params |  |  |  |  |
    | username | String | yes | 유저 ID |  |
    | password | String | yes | 유저 Password |  |
    - **🧑🏻‍💻 Body Example**
        
        ```jsx
        {
        	"username": "userid12",
        	"password": "qwer1234"
        }
        ```
        
    - **✅ Response 200**
        
        ```jsx
        { header : { Authorization : Json Web Token } }
        ```
        
    - **✅ Response 500**
        
        ```jsx
        { message: '존재하지 않는 회원입니다.' }
        ```
        
    

---

- `**[GET]` 회원 디테일 (일반 회원)**
    
    
    | Description | 유저 디테일 정보 출력 API |
    | --- | --- |
    | URL | /user/info/detail |
    | Auth Required | Yes |
    - **🧑🏻‍💻 Header Example ( Authorization )**
        
        ```jsx
        { 
        	header : { 
        		Authorization : Json Web Token 
        		} 
        }
        ```
        
    - **✅ Response 200**
        
        ```jsx
        {}
        ```
        
    - **✅ Response 404**
        
        ```jsx
        {}
        ```
        

---

- `**[POST]` 회원가입**
    
    
    | Description | 회원가입 API |
    | --- | --- |
    | URL | https://jsonplaceholder.typicode.com/posts/1/commentsjoin |
    | Auth Required | No |
    
    | Paramater | Type  | Required | Place | Description |
    | --- | --- | --- | --- | --- |
    | body params |  |  |  |  |
    | username | string | yes | body | 유저 ID |
    | password | string | yes | body | 유저 password |
    | name | String | yes | body | 이름 |
    | email | String | yes | body | 이메일 |
    | birth | String | yes | body | 나이, 생일 |
    | gender | String | yes | body | 성별 |
    | url params |  |  |  |  |
    |  |  |  |  |  |
    - **🧑🏻‍💻 Body Example (Json)**
        
        ```jsx
        {
        	"username": "userid12",
        	"password": "qwer1234",
        	"name": "김진우",
        	"email": "uesrid12@gmail.com",
        	"birth": "970321",
        	"gender": "1"
        }
        ```
        
    - **✅ Response 200**
        
        ```jsx
        {
        	"message" : "성공적으로 회원가입이 완료되었습니다"
        }
        ```
        
    - **✅ Response 500**
        
        ```jsx
        {
        	"message" : "회원가입에 실패하였습니다."
        }
        ```
        
    - **✅ Response 404**

---

- `**[MODIFY | POST]` 회원 정보 수정 (일반 회원)**
    
    
    | Description | 회원가입 되어있는 유저에 대해서 회원 정보를 수정할 수 있는 API |
    | --- | --- |
    | URL | /user/modify/user_info |
    | Auth Required | Yes |
    
    | Paramater | Type  | Required | Description | Default |
    | --- | --- | --- | --- | --- |
    | body params |  |  |  |  |
    | name | string | no | 변경할 유저 이름 | Usre Already Name |
    | email | string | no | 변경할 유저 이메일 | Usre Already Email |
    | birth | string | no | 변경할 유저 생일 | 990310 | Usre Already Birth |
    | gender | string | no | 변경할 유저 성별 | 1 | User Already Gender |
    | url params |  |  |  |  |
    |  |  |  |  |  |
    - **🧑🏻‍💻 Header Example**
        
        ```jsx
        { 
        	header : { 
        		Authorization : Json Web Token 
        		} 
        }
        ```
        
    - **✅ Response 200**
        
        ```jsx
        {}
        ```
        
    - **✅ Response 404**
        
        ```jsx
        {}
        ```
        

---

- `**[GET]` 회원 탈퇴**
    
    
    | Description | 회원 탈퇴를 위한 API |
    | --- | --- |
    | URL | /user/leave |
    | Auth Required | Yes |
    - **🧑🏻‍💻 Header Example**
        
        ```jsx
        { 
        	header : { 
        		Authorization : Json Web Token 
        		} 
        }
        ```
        
    - **✅ Response 200**
        
        ```jsx
        { 
        	message : "회원탈퇴를 성공적으로 마치셨습니다." 
        }
        ```
        
    - **✅ Response 404**
        
        ```jsx
        {
        	message : "회원탈퇴를 실패하였습니다."
        }
        ```
        

---

## 게시판

## 댓글

---

- `**[DELETE | POST]` 댓글 삭제 (일반 회원)**
    
    
    | Description | 로그인한 유저가 작성한 댓글 삭제 기능 API |
    | --- | --- |
    | URL | /user/comment/delete |
    | Auth Required | Yes |
    
    | Paramater | Type  | Required | Description | Default |
    | --- | --- | --- | --- | --- |
    | body params |  |  |  |  |
    | cmtId | Integer | Yes | 삭제할 댓글의 ID(PK) |  |
    | url params |  |  |  |  |
    |  |  |  |  |  |
    - **🧑🏻‍💻 Header Example ( Authorization )**
        
        ```jsx
         
        header : { 
        		Authorization : Json Web Token 
        } 
        ```
        
    - **✅ Response 200**
        
        ```jsx
        {}
        ```
        
    - **✅ Response 404**
        
        ```jsx
        {}
        ```
        

---

- `**[DELETE | POST]` 댓글 리스트 삭제 (일반 회원)**
    
    
    | Description | 로그인한 유저가 작성한 댓글을 선택하여 삭제 하는 기능인 API |
    | --- | --- |
    | URL | /user/comment/delete |
    | Auth Required | Yes |
    
    | Paramater | Type  | Required | Description | Default |
    | --- | --- | --- | --- | --- |
    | body params |  |  |  |  |
    | cmtIds | List<Integer> | Yes | 삭제할 댓글 ID (PK) |  |
    | url params |  |  |  |  |
    |  |  |  |  |  |
    - **🧑🏻‍💻 Header Example ( Authorization )**
        
        ```jsx
         
        header : { 
        		Authorization : Json Web Token 
        } 
        ```
        
    - **✅ Response 200**
        
        ```jsx
        {}
        ```
        
    - **✅ Response 404**
        
        ```jsx
        {}
        ```
        

---

- `**[POST]` 댓글 생성 (일반 회원)**
    
    
    | Description | 댓글 생성 API |
    | --- | --- |
    | URL | /user/comment/reg |
    | Auth Required | Yes |
    
    | Paramater | Type  | Required | Description | Default |
    | --- | --- | --- | --- | --- |
    | body params |  |  |  |  |
    | content | String | Yes | 댓글 내용 |  |
    | board_id | Integer | Yes | 댓글 연관 게시판 ID |  |
    | url params |  |  |  |  |
    |  |  |  |  |  |
    - **🧑🏻‍💻 Header Example ( Authorization )**
        
        ```jsx
         
        header : { 
        		Authorization : Json Web Token 
        } 
        ```
        
    - **✅ Response 200**
        
        ```jsx
        {}
        ```
        
    - **✅ Response 404**
        
        ```jsx
        {}
        ```
        

---

- `**[MODIFY | POST]` 댓글 수정 (일반 회원)**
    
    
    | Description | 댓글 수정 API |
    | --- | --- |
    | URL | /user/comment/modify |
    | Auth Required | Yes |
    
    | Paramater | Type  | Required | Description | Default |
    | --- | --- | --- | --- | --- |
    | body params |  |  |  |  |
    | cmtId | Integer | Yes | 변경할 댓글 ID |  |
    | content | String | No | 댓글 변경할 내용 | 기존의 댓글 내용 |
    | url params |  |  |  |  |
    |  |  |  |  |  |
    - **🧑🏻‍💻 Header Example ( Authorization )**
        
        ```jsx
         
        header : { 
        		Authorization : Json Web Token 
        } 
        ```
        
    - **✅ Response 200**
        
        ```jsx
        {}
        ```
        
    - **✅ Response 404**
        
        ```jsx
        {}
        ```
        

---

## 파일

## 1대1 문의

## QnA
