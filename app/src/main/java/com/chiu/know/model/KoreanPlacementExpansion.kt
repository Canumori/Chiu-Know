package com.chiu.know.model

/**
 * Candidate Korean placement expansion.
 *
 * This brings the engineering bank to four items per CEFR-labelled band while
 * Korean remains behind the production quality gate. The labels are internal
 * placement targets, not a claimed equivalence between CEFR and the six-level
 * Korean curricula used by external institutions. Linguistic/level calibration
 * must be independently reviewed before production rollout.
 */
val additionalKoreanPlacementQuestions = listOf(
    PlacementQuestion(
        "ko-a1-003",
        CefrLevel.A1,
        "저는 매일 아침 커피를 ___.",
        listOf("읽어요", "마셔요", "입어요", "만나요"),
        1
    ),
    PlacementQuestion(
        "ko-a1-004",
        CefrLevel.A1,
        "책상 위에 책이 ___.",
        listOf("먹어요", "가요", "있어요", "만나요"),
        2
    ),

    PlacementQuestion(
        "ko-a2-003",
        CefrLevel.A2,
        "어제 친구를 ___ 같이 영화를 봤어요.",
        listOf("만나면", "만나지만", "만날까요", "만나서"),
        3
    ),
    PlacementQuestion(
        "ko-a2-004",
        CefrLevel.A2,
        "한국어를 더 잘하고 싶어서 매일 ___.",
        listOf("공부해요", "운동해요", "요리해요", "쇼핑해요"),
        0
    ),

    PlacementQuestion(
        "ko-b1-003",
        CefrLevel.B1,
        "저는 제주도에 가 본 적이 ___.",
        listOf("갈까요", "가겠지만", "있어요", "가느라고"),
        2
    ),
    PlacementQuestion(
        "ko-b1-004",
        CefrLevel.B1,
        "밖이 아주 흐린 걸 보니 곧 비가 ___.",
        listOf("왔더라면", "오기는커녕", "오느라고", "올 것 같아요"),
        3
    ),

    PlacementQuestion(
        "ko-b2-003",
        CefrLevel.B2,
        "아무리 ___ 건강을 챙겨야 해요.",
        listOf("바빠서", "바쁘더라도", "바쁘니까", "바쁜데요"),
        1
    ),
    PlacementQuestion(
        "ko-b2-004",
        CefrLevel.B2,
        "회의가 취소될 줄 알았더라면 이렇게 일찍 ___.",
        listOf("오지 않았어요", "오지 못했어요", "오려고 했어요", "오지 않았을 거예요"),
        3
    ),

    PlacementQuestion(
        "ko-c1-003",
        CefrLevel.C1,
        "새로운 근거가 추가된 것이 아니라 기존 주장을 반복한 것에 ___.",
        listOf("기인한다", "불과하다", "해당한다", "비롯된다"),
        1
    ),
    PlacementQuestion(
        "ko-c1-004",
        CefrLevel.C1,
        "현재 조건을 모두 고려하면 사실상 계획을 처음부터 다시 세워야 하는 ___.",
        listOf("탓이다", "바람이다", "셈이다", "김이다"),
        2
    ),

    PlacementQuestion(
        "ko-c2-003",
        CefrLevel.C2,
        "그 주장은 일견 타당해 보이지만 핵심 전제가 검증되지 않아 ___ 받아들이기 어렵다.",
        listOf("한껏", "미처", "줄곧", "선뜻"),
        3
    ),
    PlacementQuestion(
        "ko-c2-004",
        CefrLevel.C2,
        "위원회의 표현은 책임 소재를 분명히 하기보다 오히려 핵심 쟁점을 ___.",
        listOf("확정하는 데 그쳤다", "흐리는 결과를 낳았다", "부각하는 셈이었다", "해명하는 데 기여했다"),
        1
    )
)

val candidateKoreanPlacementQuestions: List<PlacementQuestion> =
    starterKoreanPlacementQuestions + additionalKoreanPlacementQuestions
