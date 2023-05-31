## UseCase 분석

```mermaid
flowchart LR
    A1([장소 등록]) ~~~ A2([장소 수정]) ~~~ A5([장소 리뷰 작성])
    A3([장소 조회]) --- A6([인기 검색 순위 조회])
    A6 --- A8([조회 수])
    A6 --- A9([댓글 수])
    A6 --- A11([별점 평균])
    A3 --- A7([상세 조회]) --- A10([인기 검색 카운트 + 1])
```

### 장소 등록

```mermaid
sequenceDiagram
    actor User
    box Client
        participant Browser
        participant Kakao
    end
    box Server
        participant adapter.rest
        participant service
        participant adapter.persistence
    end

    User ->> Browser: 장소검색

    activate Browser
    Browser ->> Kakao: Kakao Api Search
    activate Kakao
    Kakao -->> Browser: Result
    deactivate Kakao
    Browser -->> User: Result
    deactivate Browser

    User ->> Browser: 장소선택 및 설명 작성
    activate Browser
    Browser ->> adapter.rest: POST /place (RegisterRequest)
    activate adapter.rest
    adapter.rest ->> service: RegisterCommand
    activate service
    service ->> adapter.persistence: Place
    activate adapter.persistence
    adapter.persistence -->> service: Id
    deactivate adapter.persistence
    service -->> adapter.rest: Id
    deactivate service
    adapter.rest -->> Browser: Id
    deactivate adapter.rest
    Browser -->> User: 등록 완료
    deactivate Browser
```

### 장소 조회

```mermaid
flowchart LR
    subgraph Client
        A1("Call PlaceSearch Api")
    end
    subgraph Server
        A1 -->|1| A`1
        A`1("port.in SearchPlaceUseCase") -->|2| A'2("port.out SearchPlacePort") -->|3| A'3[("Query")]
    end
```