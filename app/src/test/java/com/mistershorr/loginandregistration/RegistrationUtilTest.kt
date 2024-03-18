package com.mistershorr.loginandregistration

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class RegistrationUtilTest {
    // methodName_someCondition_expectedResult
    @Test
    fun validatePassword_emptyPassword_isFalse() {
        val actual = RegistrationUtil.validatePassword("", "")
        // assertThat(actualValue).isEqual(desiredValue)
        assertThat(actual).isFalse()
    }

    @Test
    fun validatePassword_passwordsDontMatch_isFalse() {
        val actual = RegistrationUtil.validatePassword("A1bcdefgh",
            "1Abcdefgh")
        assertThat(actual).isFalse()
    }

    @Test
    fun validatePassword_passwordTooShort_isFalse() {
        val actual = RegistrationUtil.validatePassword("A1bc",
            "A1bc")
        assertThat(actual).isFalse()
    }

    // Make tests for failures of
        // min length of 8 chars
        // at least one digit   (make sure it's at least 8 and has a capital letter)
        // at least on capital letter
    // Make a test for good matching passwords working

    // Make the tests for the other functions in the Util class with
    // the common failures and 1 success for each

    // When done with all unit tests & they are successful, implement the validation in your
    // RegistrationActivity. It should not register if anything is not valid.
}