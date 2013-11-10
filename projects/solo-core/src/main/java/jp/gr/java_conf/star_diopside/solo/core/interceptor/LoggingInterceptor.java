package jp.gr.java_conf.star_diopside.solo.core.interceptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.gr.java_conf.star_diopside.solo.core.exception.IBusinessException;
import jp.gr.java_conf.star_diopside.solo.core.logging.Loggable;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.commons.logging.Log;
import org.springframework.aop.interceptor.AbstractTraceInterceptor;

/**
 * ログ出力インターセプター
 */
@SuppressWarnings("serial")
public class LoggingInterceptor extends AbstractTraceInterceptor {

    /** メソッド名のデフォルトプレースホルダー */
    private static final String DEFAULT_PLACEHOLDER_METHOD_NAME = "$[methodName]";

    /** 実行対象クラスの完全修飾名のデフォルトプレースホルダー */
    private static final String DEFAULT_PLACEHOLDER_TARGET_CLASS_NAME = "$[targetClassName]";

    /** 実行対象クラスの単純名のデフォルトプレースホルダー */
    private static final String DEFAULT_PLACEHOLDER_TARGET_CLASS_SHORT_NAME = "$[targetClassShortName]";

    /** 戻り値のデフォルトプレースホルダー */
    private static final String DEFAULT_PLACEHOLDER_RETURN_VALUE = "$[returnValue]";

    /** メソッド仮引数型のデフォルトプレースホルダー */
    private static final String DEFAULT_PLACEHOLDER_ARGUMENT_TYPES = "$[argumentTypes]";

    /** メソッド引数インデックスのデフォルトプレースホルダー */
    private static final String DEFAULT_PLACEHOLDER_ARGUMENT_INDEX = "$[argumentIndex]";

    /** メソッド実引数値のデフォルトプレースホルダー */
    private static final String DEFAULT_PLACEHOLDER_ARGUMENT_VALUE = "$[argumentValue]";

    /** 例外メッセージのデフォルトプレースホルダー */
    private static final String DEFAULT_PLACEHOLDER_EXCEPTION = "$[exception]";

    /** 実行時間(ミリ秒)のデフォルトプレースホルダー */
    private static final String DEFAULT_PLACEHOLDER_INVOCATION_TIME = "$[invocationTime]";

    /** プレースホルダーの正規表現 */
    private static final Pattern PATTERN_PLACEHOLDER = Pattern.compile("\\$\\[\\p{Alnum}+\\]");

    /** 開始ログフォーマットのデフォルト値 */
    private static final String DEFAULT_ENTER_MESSAGE = "[START] " + DEFAULT_PLACEHOLDER_TARGET_CLASS_NAME + "."
            + DEFAULT_PLACEHOLDER_METHOD_NAME + "(" + DEFAULT_PLACEHOLDER_ARGUMENT_TYPES + ")";

    /** 終了ログフォーマットのデフォルト値 */
    private static final String DEFAULT_EXIT_MESSAGE = "[END] " + DEFAULT_PLACEHOLDER_TARGET_CLASS_NAME + "."
            + DEFAULT_PLACEHOLDER_METHOD_NAME + "(" + DEFAULT_PLACEHOLDER_ARGUMENT_TYPES + ") : invocationTime = "
            + DEFAULT_PLACEHOLDER_INVOCATION_TIME + " [ms]";

    /** 例外ログフォーマットのデフォルト値 */
    private static final String DEFAULT_EXCEPTION_MESSAGE = "Exception thrown in method '"
            + DEFAULT_PLACEHOLDER_METHOD_NAME + "' of class [" + DEFAULT_PLACEHOLDER_TARGET_CLASS_NAME + "], thrown "
            + DEFAULT_PLACEHOLDER_EXCEPTION;

    /** メソッド引数ログフォーマットのデフォルト値 */
    private static final String DEFAULT_ARGUMENTS_MESSAGE = "INPUT[" + DEFAULT_PLACEHOLDER_ARGUMENT_INDEX + "]: "
            + DEFAULT_PLACEHOLDER_ARGUMENT_VALUE;

    /** 戻り値ログフォーマットのデフォルト値 */
    private static final String DEFAULT_RESULT_MESSAGE = "OUTPUT: " + DEFAULT_PLACEHOLDER_RETURN_VALUE;

    /** メソッド名のプレースホルダー */
    private String placeholderMethodName = DEFAULT_PLACEHOLDER_METHOD_NAME;

    /** 実行対象クラスの完全修飾名のプレースホルダー */
    private String placeholderTargetClassName = DEFAULT_PLACEHOLDER_TARGET_CLASS_NAME;

    /** 実行対象クラスの単純名のプレースホルダー */
    private String placeholderTargetClassShortName = DEFAULT_PLACEHOLDER_TARGET_CLASS_SHORT_NAME;

    /** 戻り値のプレースホルダー */
    private String placeholderReturnValue = DEFAULT_PLACEHOLDER_RETURN_VALUE;

    /** メソッド仮引数型のプレースホルダー */
    private String placeholderArgumentTypes = DEFAULT_PLACEHOLDER_ARGUMENT_TYPES;

    /** メソッド引数インデックスのプレースホルダー */
    private String placeholderArgumentIndex = DEFAULT_PLACEHOLDER_ARGUMENT_INDEX;

    /** メソッド実引数値のプレースホルダー */
    private String placeholderArgumentValue = DEFAULT_PLACEHOLDER_ARGUMENT_VALUE;

    /** 例外メッセージのプレースホルダー */
    private String placeholderException = DEFAULT_PLACEHOLDER_EXCEPTION;

    /** 実行時間(ミリ秒)のプレースホルダー */
    private String placeholderInvocationTime = DEFAULT_PLACEHOLDER_INVOCATION_TIME;

    /** 開始ログフォーマット */
    private String enterMessage = DEFAULT_ENTER_MESSAGE;

    /** 終了ログフォーマット */
    private String exitMessage = DEFAULT_EXIT_MESSAGE;

    /** 例外ログフォーマット */
    private String exceptionMessage = DEFAULT_EXCEPTION_MESSAGE;

    /** メソッド引数ログフォーマット */
    private String argumentsMessage = DEFAULT_ARGUMENTS_MESSAGE;

    /** 戻り値ログフォーマット */
    private String resultMessage = DEFAULT_RESULT_MESSAGE;

    /**
     * メソッド名のプレースホルダーを設定する。
     * 
     * @param placeholderMethodName メソッド名のプレースホルダー
     */
    public void setPlaceholderMethodName(String placeholderMethodName) {
        if (!PATTERN_PLACEHOLDER.matcher(placeholderMethodName).matches()) {
            throw new IllegalArgumentException("Parameter don't match the pattern of the placeholder.");
        }
        this.placeholderMethodName = placeholderMethodName;
    }

    /**
     * 実行対象クラスの完全修飾名のプレースホルダーを設定する。
     * 
     * @param placeholderTargetClassName 実行対象クラスの完全修飾名のプレースホルダー
     */
    public void setPlaceholderTargetClassName(String placeholderTargetClassName) {
        if (!PATTERN_PLACEHOLDER.matcher(placeholderTargetClassName).matches()) {
            throw new IllegalArgumentException("Parameter don't match the pattern of the placeholder.");
        }
        this.placeholderTargetClassName = placeholderTargetClassName;
    }

    /**
     * 実行対象クラスの単純名のプレースホルダーを設定する。
     * 
     * @param placeholderTargetClassShortName 実行対象クラスの単純名のプレースホルダー
     */
    public void setPlaceholderTargetClassShortName(String placeholderTargetClassShortName) {
        if (!PATTERN_PLACEHOLDER.matcher(placeholderTargetClassShortName).matches()) {
            throw new IllegalArgumentException("Parameter don't match the pattern of the placeholder.");
        }
        this.placeholderTargetClassShortName = placeholderTargetClassShortName;
    }

    /**
     * 戻り値のプレースホルダーを設定する。
     * 
     * @param placeholderReturnValue 戻り値のプレースホルダー
     */
    public void setPlaceholderReturnValue(String placeholderReturnValue) {
        if (!PATTERN_PLACEHOLDER.matcher(placeholderReturnValue).matches()) {
            throw new IllegalArgumentException("Parameter don't match the pattern of the placeholder.");
        }
        this.placeholderReturnValue = placeholderReturnValue;
    }

    /**
     * メソッド仮引数型のプレースホルダーを設定する。
     * 
     * @param placeholderArgumentTypes メソッド仮引数型のプレースホルダー
     */
    public void setPlaceholderArgumentTypes(String placeholderArgumentTypes) {
        if (!PATTERN_PLACEHOLDER.matcher(placeholderArgumentTypes).matches()) {
            throw new IllegalArgumentException("Parameter don't match the pattern of the placeholder.");
        }
        this.placeholderArgumentTypes = placeholderArgumentTypes;
    }

    /**
     * メソッド引数インデックスのプレースホルダーを設定する。
     * 
     * @param placeholderArgumentIndex メソッド引数インデックスのプレースホルダー
     */
    public void setPlaceholderArgumentIndex(String placeholderArgumentIndex) {
        if (!PATTERN_PLACEHOLDER.matcher(placeholderArgumentIndex).matches()) {
            throw new IllegalArgumentException("Parameter don't match the pattern of the placeholder.");
        }
        this.placeholderArgumentIndex = placeholderArgumentIndex;
    }

    /**
     * メソッド実引数値のプレースホルダーを設定する。
     * 
     * @param placeholderArgumentValue メソッド実引数値のプレースホルダー
     */
    public void setPlaceholderArgumentValue(String placeholderArgumentValue) {
        if (!PATTERN_PLACEHOLDER.matcher(placeholderArgumentValue).matches()) {
            throw new IllegalArgumentException("Parameter don't match the pattern of the placeholder.");
        }
        this.placeholderArgumentValue = placeholderArgumentValue;
    }

    /**
     * 例外メッセージのプレースホルダーを設定する。
     * 
     * @param placeholderException 例外メッセージのプレースホルダー
     */
    public void setPlaceholderException(String placeholderException) {
        if (!PATTERN_PLACEHOLDER.matcher(placeholderException).matches()) {
            throw new IllegalArgumentException("Parameter don't match the pattern of the placeholder.");
        }
        this.placeholderException = placeholderException;
    }

    /**
     * 実行時間(ミリ秒)のプレースホルダーを設定する。
     * 
     * @param placeholderInvocationTime 実行時間(ミリ秒)のプレースホルダー
     */
    public void setPlaceholderInvocationTime(String placeholderInvocationTime) {
        if (!PATTERN_PLACEHOLDER.matcher(placeholderInvocationTime).matches()) {
            throw new IllegalArgumentException("Parameter don't match the pattern of the placeholder.");
        }
        this.placeholderInvocationTime = placeholderInvocationTime;
    }

    /**
     * 開始ログのメッセージフォーマットを設定する。
     * 
     * @param enterMessage 開始ログのメッセージフォーマット
     */
    public void setEnterMessage(String enterMessage) {
        this.enterMessage = enterMessage;
    }

    /**
     * 終了ログのメッセージフォーマットを設定する。
     * 
     * @param exitMessage 終了ログのメッセージフォーマット
     */
    public void setExitMessage(String exitMessage) {
        this.exitMessage = exitMessage;
    }

    /**
     * 例外ログのメッセージフォーマットを設定する。
     * 
     * @param exceptionMessage 例外ログのメッセージフォーマット
     */
    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    /**
     * メソッド引数ログのメッセージフォーマットを設定する。
     * 
     * @param argumentsMessage メソッド引数ログのメッセージフォーマット
     */
    public void setArgumentsMessage(String argumentsMessage) {
        this.argumentsMessage = argumentsMessage;
    }

    /**
     * 戻り値ログのメッセージフォーマットを設定する。
     * 
     * @param resultMessage 戻り値ログのメッセージフォーマット
     */
    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    @Override
    protected Object invokeUnderTrace(MethodInvocation invocation, Log logger) throws Throwable {

        StopWatch stopWatch = new StopWatch();
        Map<String, ?> base = createReplacementMap(invocation);
        HashMap<String, Object> addition = new HashMap<>();
        StringBuffer sb = new StringBuffer();

        try {
            // 実行時間の計測を開始する
            stopWatch.start();

            // 開始ログを出力する
            if (this.isEnterLogEnabled(logger)) {
                this.writeToEnterLog(logger, replacePlaceholders(this.enterMessage, sb, base));
            }

            // 入力パラメータログを出力する
            if (this.isArgumentsLogEnabled(logger)) {
                Object[] arguments = invocation.getArguments();
                addition.clear();
                for (int i = 0; i < arguments.length; i++) {
                    addition.put(this.placeholderArgumentIndex, Integer.valueOf(i));
                    for (Object data : createPrintableObjects(arguments[i])) {
                        addition.put(this.placeholderArgumentValue, data);
                        this.writeToArgumentsLog(logger, replacePlaceholders(this.argumentsMessage, sb, base, addition));
                    }
                }
            }

            // 処理を実行する
            Object result = invocation.proceed();

            // 出力パラメータログを出力する
            if (this.isResultLogEnabled(logger)) {
                // 戻り値が void 以外の場合にログ出力する
                if (!invocation.getMethod().getReturnType().equals(Void.TYPE)) {
                    addition.clear();
                    for (Object data : createPrintableObjects(result)) {
                        addition.put(this.placeholderReturnValue, data);
                        this.writeToResultLog(logger, replacePlaceholders(this.resultMessage, sb, base, addition));
                    }
                }
            }

            return result;

        } catch (Throwable t) {
            // エラーログを出力する
            if (this.isExceptionLogEnabled(logger, t)) {
                addition.clear();
                addition.put(this.placeholderException, t);
                this.writeToExceptionLog(logger, replacePlaceholders(this.exceptionMessage, sb, base, addition), t);
            }

            // 例外を再スローする
            throw t;

        } finally {
            // 終了ログを出力する
            if (this.isExitLogEnabled(logger)) {
                addition.clear();
                addition.put(this.placeholderInvocationTime, Long.valueOf(stopWatch.getTime()));
                this.writeToExitLog(logger, replacePlaceholders(this.exitMessage, sb, base, addition));
            }
        }
    }

    /**
     * 開始ログを出力する。
     * 
     * @param logger ログ出力インスタンス
     * @param message ログ出力メッセージ
     */
    protected void writeToEnterLog(Log logger, Object message) {
        logger.info(message);
    }

    /**
     * 終了ログを出力する。
     * 
     * @param logger ログ出力インスタンス
     * @param message ログ出力メッセージ
     */
    protected void writeToExitLog(Log logger, Object message) {
        logger.info(message);
    }

    /**
     * 例外ログを出力する。
     * 
     * @param logger ログ出力インスタンス
     * @param message ログ出力メッセージ
     * @param t 例外インスタンス
     */
    protected void writeToExceptionLog(Log logger, Object message, Throwable t) {
        if (t == null) {
            logger.error(message);
        } else if (t instanceof IBusinessException) {
            logger.info(message);
        } else {
            logger.error(message, t);
        }
    }

    /**
     * メソッド引数ログを出力する。
     * 
     * @param logger ログ出力インスタンス
     * @param message ログ出力メッセージ
     */
    protected void writeToArgumentsLog(Log logger, Object message) {
        logger.debug(message);
    }

    /**
     * 戻り値ログを出力する。
     * 
     * @param logger ログ出力インスタンス
     * @param message ログ出力メッセージ
     */
    protected void writeToResultLog(Log logger, Object message) {
        logger.debug(message);
    }

    /**
     * 開始ログの出力が有効かどうかを判定する。
     * 
     * @param logger ログ出力インスタンス
     * @return ログ出力を有効にする場合はtrueを返す。
     */
    protected boolean isEnterLogEnabled(Log logger) {
        return logger.isInfoEnabled();
    }

    /**
     * 終了ログの出力が有効かどうかを判定する。
     * 
     * @param logger ログ出力インスタンス
     * @return ログ出力を有効にする場合はtrueを返す。
     */
    protected boolean isExitLogEnabled(Log logger) {
        return logger.isInfoEnabled();
    }

    /**
     * 例外ログの出力が有効かどうかを判定する。
     * 
     * @param logger ログ出力インスタンス
     * @param t 例外インスタンス
     * @return ログ出力を有効にする場合はtrueを返す。
     */
    protected boolean isExceptionLogEnabled(Log logger, Throwable t) {
        if (t instanceof IBusinessException) {
            return logger.isInfoEnabled();
        } else {
            return logger.isErrorEnabled();
        }
    }

    /**
     * メソッド引数ログの出力が有効かどうかを判定する。
     * 
     * @param logger ログ出力インスタンス
     * @return ログ出力を有効にする場合はtrueを返す。
     */
    protected boolean isArgumentsLogEnabled(Log logger) {
        return logger.isDebugEnabled();
    }

    /**
     * 戻り値ログの出力が有効かどうかを判定する。
     * 
     * @param logger ログ出力インスタンス
     * @return ログ出力を有効にする場合はtrueを返す。
     */
    protected boolean isResultLogEnabled(Log logger) {
        return logger.isDebugEnabled();
    }

    /**
     * {@link org.aopalliance.intercept.MethodInvocation MethodInvocation}
     * インスタンスからプレースホルダー置換文字列マップを生成する。
     * 
     * @param invocation メソッド実行インスタンス
     * @return 置換文字列マップ
     */
    private Map<String, ?> createReplacementMap(MethodInvocation invocation) {

        HashMap<String, Object> replacement = new HashMap<>();

        replacement.put(this.placeholderTargetClassName, getClassForLogging(invocation.getThis()).getName());
        replacement.put(this.placeholderTargetClassShortName, getClassForLogging(invocation.getThis()).getSimpleName());
        replacement.put(this.placeholderMethodName, invocation.getMethod().getName());

        Class<?>[] argumentTypes = invocation.getMethod().getParameterTypes();
        ArrayList<String> argumentTypeShortNames = new ArrayList<>(argumentTypes.length);
        for (Class<?> type : argumentTypes) {
            argumentTypeShortNames.add(type.getSimpleName());
        }
        replacement.put(this.placeholderArgumentTypes, StringUtils.join(argumentTypeShortNames, ','));

        return replacement;
    }

    /**
     * オブジェクトのインスタンスからログ出力用文字列を生成する。
     * 
     * @param obj ログ出力内容の文字列コレクション
     */
    private static Collection<?> createPrintableObjects(Object obj) {

        if (obj == null) {
            return Arrays.asList((Object) null);
        } else if (obj instanceof Loggable) {
            Collection<String> logText = ((Loggable) obj).toLogText();
            if (logText == null) {
                return Arrays.asList((Object) null);
            } else {
                return logText;
            }
        } else {
            return Arrays.asList(obj);
        }
    }

    /**
     * プレースホルダーを置換したメッセージを取得する。
     * 
     * @param message プレースホルダーを含む文字列
     * @param sb 変換結果を格納する文字列バッファ。nullを指定した場合、新しい文字列バッファを生成する。
     * @param replacement 置換文字列マップ。nullの場合は置換を行わない。
     * @return プレースホルダーを置換した結果を格納する文字列バッファ
     */
    private static StringBuffer replacePlaceholders(String message, StringBuffer sb, Map<String, ?> replacement) {
        return replacePlaceholders(message, sb, replacement, null);
    }

    /**
     * プレースホルダーを置換したメッセージを取得する。
     * 
     * @param message プレースホルダーを含む文字列
     * @param sb 変換結果を格納する文字列バッファ。nullを指定した場合、新しい文字列バッファを生成する。
     * @param base 置換文字列マップ。nullの場合は置換を行わない。
     * @param addition 追加の置換文字列マップ。nullの場合は置換を行わない。
     * @return プレースホルダーを置換した結果を格納する文字列バッファ
     */
    private static StringBuffer replacePlaceholders(String message, StringBuffer sb, Map<String, ?> base,
            Map<String, ?> addition) {

        StringBuffer output;

        if (sb == null) {
            output = new StringBuffer();
        } else {
            output = sb;
            output.setLength(0);
        }

        if (base == null && addition == null) {
            output.append(message);
        } else {
            Matcher matcher = PATTERN_PLACEHOLDER.matcher(message);
            while (matcher.find()) {
                String match = matcher.group();
                if (base != null && base.containsKey(match)) {
                    matcher.appendReplacement(output, Matcher.quoteReplacement(String.valueOf(base.get(match))));
                } else if (addition != null && addition.containsKey(match)) {
                    matcher.appendReplacement(output, Matcher.quoteReplacement(String.valueOf(addition.get(match))));
                } else {
                    matcher.appendReplacement(output, Matcher.quoteReplacement(match));
                }
            }
            matcher.appendTail(output);
        }

        return output;
    }
}
