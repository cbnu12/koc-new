## UseCase 분석

```mermaid
flowchart LR
    A1([검색어 조회수 증가])
    A2([검색어-인기 조회])
```

### 키워드 카운트 증가

```mermaid
sequenceDiagram
    participant KeywordMQ
    participant Keyword
```