package com.system.core.wrapper

/*
일반적인 rest api는 jackson 라이브러리가 직렬화 해주지만,
파일 저장이나 캐싱 등에선 직렬화 해주는 작업이 필요하다.
 */
interface ResuntInterface<T> : java.io.Serializable {

    val result: T?
}