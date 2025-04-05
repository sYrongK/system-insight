System Insight

변수
1. 변수명을 먼저 선언하고 그 다음에 타입을 선언한다. (타입은 생략 가능) val num: Int, val num
2. non-null 프로퍼티는 반드시 초기값을 할당하거나, 생성자에서 초기화를 해야 한다. 그렇지 않으면 컴파일 에러 발생한다.
3. return result!! -> result 변수가 nullable이라면 강제로 non-null로 변환하는 것으로, 값이 Null이면 exception 발생한다.
4. T? -> 타입 T에 null 허용
5. 자동 생성된 getter에 접근할 때 .getXX()가 아니라 .XX 로 호출한다.

method 
1. func 로 선언
2. return type은 메서드 인자 뒤에 선언한다.
3. 인터페이스에 getResult() 선언해두고, 구현 클래스에서 var result를 선언하면 자동으로 생성된 fun getResult(): T?가 인터페이스의 구현으로 인식되지 않습니다. 그래서 메서드 implements하라고 나오는데, 하면 같은 메서드 2개로 인식해서 충돌난다. 이럴 경우 변수를 오버라이딩 하면 된다. override var result: T? = null

keyword
1. new 키워드 사용 안 한다. 생성자 직접 호출하여 객체 생성
2. val: 불변 프로퍼티 -> getter만 생성된다. var: 가변 프로퍼티 -> getter, setter만 생성된다.

Lombok
1. Lombok 지원하지 않는다.