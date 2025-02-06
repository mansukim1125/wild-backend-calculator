# 계산기 API

RESTful API 인터페이스를 통해 계산기 기능을 제공하는 간단한 HTTP 서버입니다. 기본적인 사칙 연산을 지원하고 계산 기록을 유지합니다.

## 기능

- 기본 사칙 연산 (덧셈, 뺄셈, 곱셈, 나눗셈)
- 계산 기록 조회
- JSON 요청/응답 지원

## API 엔드포인트

### 계산 생성

계산을 수행하고 기록에 저장합니다.

```
POST /calculations

요청 본문:
{
    "operator": string,  // 다음 중 하나: "+", "-", "*", "/"
    "lhs": integer,      // 좌측 피연산자
    "rhs": integer       // 우측 피연산자
}

응답 본문:
{
    "result": integer    // 계산 결과
}
```

### 계산 목록 조회

모든 계산 기록을 조회합니다.

```
GET /calculations

응답 본문:
{
    "calculations": [
        {
            "lhs": integer,      // 좌측 피연산자
            "rhs": integer,      // 우측 피연산자
            "operator": string,  // 사용된 연산자
            "result": integer    // 계산 결과
        },
        ...
    ]
}
```

## 프로젝트 구조

- `com.example.demo`
    - `application/`: 비즈니스 로직 계층 (계산기 및 연산자)
    - `domain/`: 도메인 모델 (계산)
    - `dto/`: API 요청/응답용 데이터 전송 객체
    - `persistence/`: 데이터 저장 계층
    - `presentation/`: HTTP 서버 및 요청 처리

## 사용 예시

### 계산 수행

```bash
curl -X POST http://localhost:8080/calculations \
  -H "Content-Type: application/json" \
  -d '{"operator": "+", "lhs": 5, "rhs": 3}'
```

응답:
```json
{
    "result": 8
}
```

### 계산 기록 조회

```bash
curl http://localhost:8080/calculations
```

응답:
```json
{
    "calculations": [
        {
            "lhs": 5,
            "rhs": 3,
            "operator": "+",
            "result": 8
        }
    ]
}
```
