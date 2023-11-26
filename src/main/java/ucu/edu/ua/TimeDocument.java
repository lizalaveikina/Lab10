package ucu.edu.ua;

import lombok.SneakyThrows;

public class TimeDocument implements Document {
    private Document document;

    public TimeDocument(Document document) {
        this.document = document;
    }

    @Override
    @SneakyThrows
    public String parse() {
        long startTime = System.currentTimeMillis();
        String result = document.parse();
        long endTime = System.currentTimeMillis();

        System.out.println(endTime - startTime);
        return result;
    }
}

