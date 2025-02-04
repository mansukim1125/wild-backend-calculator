# Wild Backend Calculator application

## 요구사항
- 사칙연산이 지원되는 계산기 API 를 구현한다.

## API 명세

### POST /calculations
#### Request Body
```json
{
  "operator": "+",
  "lhs": 1,
  "rhs": 3
}
```

#### Response Body

```json
{
  "id": "10eb347d-f7c0-48e6-837f-69a7d1fe8919",
  "requestData": {
    "operator": "+",
    "lhs": 1,
    "rhs": 3
  },
  "result": 4
}
```

### GET /calculations

#### Response Body

```json
{
  "calculations": [
    {
      "id": "10eb347d-f7c0-48e6-837f-69a7d1fe8919",
      "requestData": {
        "operator": "+",
        "lhs": 1,
        "rhs": 3
      },
      "result": 4
    }
  ]
}
```
