package com.devtamuno.composeblurhash

import kotlinx.coroutines.ExperimentalCoroutinesApi


/**
 * Marks declarations in the library as experimental due to the fact that
 * @see ExperimentalCoroutinesApi is currently within the project already.
 * whenever this API has a breaking changes, we'll have to update this library as well.
 */
@MustBeDocumented
@Retention(value = AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.FIELD,
    AnnotationTarget.LOCAL_VARIABLE,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.TYPEALIAS
)
@RequiresOptIn(level = RequiresOptIn.Level.WARNING)
annotation class ExperimentalComposeBlurHash