
class File {
    public static void main(String[] args) {
        String scanner = args[0];
        String text = scanner;
        String result = "";
        int count = 0;
        if (text.length() != 0) {
            count++;
            for (int i = 0; i < text.length(); i++) {
                result += text.charAt(i);
                if (text.charAt(i) == ' ') {
                    count++;
                }
                if (text.charAt(i) == '?' || text.charAt(i) == '.' || text.charAt(i) == '!') {
                    System.out.println(result + "- " + count);
                    result = "";
                    count = 0;
                }
            }
        }
    }
}
