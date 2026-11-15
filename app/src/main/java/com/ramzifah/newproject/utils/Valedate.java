package com.ramzifah.newproject.utils;

import java.util.regex.Pattern;

public class Valedate {

    // 1. Email Regex
    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

    // 2. Phone Number Regex
    private static final String PHONE_REGEX = "^(\\+\\d{1,3}[- ]?)?(\\(?\\d{3}\\)?[- ]?)?\\d{3}[- ]?\\d{4}$";

    // 3. Password Regex
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$";

    // 4. Name Regex: يدعم الحروف، المسافات، الشرطات (-)، الفاصلة العليا (')، والنقاط (.) بطول من 2 إلى 50 حرفاً
    private static final String NAME_REGEX = "^[\\p{L} .'-]{2,50}$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);
    private static final Pattern NAME_PATTERN = Pattern.compile(NAME_REGEX);

    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidPhone(String phone) {
        if (phone == null) return false;
        return PHONE_PATTERN.matcher(phone).matches();
    }

    public static boolean isValidPassword(String password) {
        if (password == null) return false;
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    // دالة التحقق من الاسم الجديدة
    public static boolean isValidName(String name) {
        if (name == null) return false;
        return NAME_PATTERN.matcher(name.trim()).matches();
    }
}