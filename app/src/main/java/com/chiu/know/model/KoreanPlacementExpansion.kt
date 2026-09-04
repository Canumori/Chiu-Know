package com.chiu.know.model

/**
 * Candidate Korean placement expansion.
 *
 * This brings the engineering bank to four items per CEFR-labelled band while
 * Korean remains behind the production quality gate. The labels are internal
 * placement targets, not a claimed equivalence between CEFR and the six-level
 * Korean curricula used by external institutions. Linguistic/level calibration
 * must be reviewed before production rollout.
 */
val additionalKoreanPlacementQuestions = listOf(
    PlacementQuestion(
        "ko-a1-003",
        CefrLevel.A1,
        "저는 매일 아침 커피를 ___.",
        listOf("마셔요", "마셔서", "마시면", "마셨지만"),
        0
    ),
    PlacementQuestion(
        "ko-a1-004",
        CefrLevel.A1,
        "책상 위에 책이 ___.",
        listOf("있어요", "먹어요", "가요", "만나요"),
        0
    ),

    PlacementQuestion(
        "ko-a2-003",
        CefrLevel.A2,
        "어제 친구를 ___ 같이 영화를 봤어요.",
        listOf("만나서", "만나면", "만나지만", "만날까요"),
        0
    ),
    PlacementQuestion(
        "ko-a2-004",
        CefrLevel.A2,
        "한국어를 더 잘하고 싶어서 매일 ___.",
        listOf("공부해요", "공부했더라면", "공부하기는커녕", "공부할 리가 없어요"),
        0
    ),

    PlacementQuestion(
        "ko-b1-003",
        CefrLevel.B1,
        "저는 제주도에 가 본 적이 ___.",
        listOf("있어요", "갈까요", "가겠지만", "가느라고"),
        0
    ),
    PlacementQuestion(
        "ko-b1-004",
        CefrLevel.B1,
        "밖이 아주 흐린 걸 보니 곧 비가 ___.",
        listOf("올 것 같아요", "왔더라면", "오기는커녕", "오느라고"),
        0
    ),

    PlacementQuestion(
        "ko-b2-003",
        CefrLevel.B2,
        "아무리 ___ 건강을 챙겨야 해요.",
        listOf("바쁘더라도", "바빠서", "바쁘니까", "바쁜데요"),
        0
    ),
    PlacementQuestion(
        "ko-b2-004",
        CefrLevel.B2,
        "회의가 취소될 줄 알았더라면 이렇게 일찍 ___.",
        listOf("오지 않았을 거예요", "오지 않아요", "오지 않을까요", "오지 못해요"),
        0
    ),

    PlacementQuestion(
        "ko-c1-003",
        CefrLevel.C1,
        "새로운 근거가 추가된 것이 아니라 기존 주장을 반복한 것에 ___.",
        listOf("불과하다", "따라서다", "비롯한다", "마련이다가"),
        0
    ),
    PlacementQuestion(
        "ko-c1-004",
        CefrLevel.C1,
        "현재 조건을 모두 고려하면 사실상 계획을 처음부터 다시 세워야 하는 ___.",
        listOf("셈이다", "탓이다", "바람이다", "김이다"),
        0
    ),

    PlacementQuestion(
        "ko-c2-003",
        CefrLevel.C2,
        "그 주장은 일견 타당해 보이지만 핵심 전제가 검증되지 않아 ___ 받아들이기 어렵다.",
        listOf("선뜻", "한껏", "미처", "줄곧"),
        0
    ),
    PlacementQuestion(
        "ko-c2-004",
        CefrLevel.C2,
        "위원회의 표현은 책임 소재를 분명히 하기보다 오히려 핵심 쟁점을 ___.",
        listOf("흐리는 데 가까웠다", "흐리게 될 뻔만 했다", "흐리도록만 있었다", "흐린 적으로 삼았다"),
        0
    )
)

val candidateKoreanPlacementQuestions: List<PlacementQuestion> =
    starterKoreanPlacementQuestions + additionalKoreanPlacementQuestions
