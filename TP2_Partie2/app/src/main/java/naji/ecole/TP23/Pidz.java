package naji.ecole.TP23;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Objects;


public class Pidz {
    private String name;
    private double price;
    private int nocp0;
    private static int noCom = 1;

    public Pidz(String name, double price) {
        this.name = name;
        this.price = price;
        this.nocp0 = noCom++;
    }

    public int getNocp0() {
        return nocp0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pidz pidz = (Pidz) o;
        return Double.compare(price, pidz.price) == 0 && Objects.equals(name, pidz.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public int length() {
        return name.length();
    }

    public boolean isEmpty() {
        return name.isEmpty();
    }

    public char charAt(int i) {
        return name.charAt(i);
    }

    public int codePointAt(int index) {
        return name.codePointAt(index);
    }

    public int codePointBefore(int index) {
        return name.codePointBefore(index);
    }

    public int codePointCount(int beginIndex, int endIndex) {
        return name.codePointCount(beginIndex, endIndex);
    }

    public int offsetByCodePoints(int index, int codePointOffset) {
        return name.offsetByCodePoints(index, codePointOffset);
    }

    public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
        name.getChars(srcBegin, srcEnd, dst, dstBegin);
    }

    public byte[] getBytes(String charsetName) throws UnsupportedEncodingException {
        return name.getBytes(charsetName);
    }

    public byte[] getBytes(Charset charset) {
        return name.getBytes(charset);
    }

    public byte[] getBytes() {
        return name.getBytes();
    }

    public boolean contentEquals(StringBuffer sb) {
        return name.contentEquals(sb);
    }

    public boolean contentEquals(CharSequence cs) {
        return name.contentEquals(cs);
    }

    public boolean equalsIgnoreCase(String anotherString) {
        return name.equalsIgnoreCase(anotherString);
    }

    public int compareTo(String s) {
        return name.compareTo(s);
    }

    public int compareToIgnoreCase(String str) {
        return name.compareToIgnoreCase(str);
    }

    public boolean regionMatches(int toffset, String other, int ooffset, int len) {
        return name.regionMatches(toffset, other, ooffset, len);
    }

    public boolean regionMatches(boolean ignoreCase, int toffset, String other, int ooffset, int len) {
        return name.regionMatches(ignoreCase, toffset, other, ooffset, len);
    }

    public boolean startsWith(String prefix, int toffset) {
        return name.startsWith(prefix, toffset);
    }

    public boolean startsWith(String prefix) {
        return name.startsWith(prefix);
    }

    public boolean endsWith(String suffix) {
        return name.endsWith(suffix);
    }

    public int indexOf(int ch) {
        return name.indexOf(ch);
    }

    public int indexOf(int ch, int fromIndex) {
        return name.indexOf(ch, fromIndex);
    }

    public int lastIndexOf(int ch) {
        return name.lastIndexOf(ch);
    }

    public int lastIndexOf(int ch, int fromIndex) {
        return name.lastIndexOf(ch, fromIndex);
    }

    public int indexOf(String str) {
        return name.indexOf(str);
    }

    public int indexOf(String str, int fromIndex) {
        return name.indexOf(str, fromIndex);
    }

    public int lastIndexOf(String str) {
        return name.lastIndexOf(str);
    }

    public int lastIndexOf(String str, int fromIndex) {
        return name.lastIndexOf(str, fromIndex);
    }
}