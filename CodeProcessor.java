import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;

class CodeProcessor {
    public static void main(String[] args) {
        long totalLines = 0;
        long totalTodos = 0;
        long singleLineComments = 0;
        long totalCommentBlocks = 0;
        long totalLinesInCommentBlock = 0;
        boolean inBlock = false; //to know if we are in a block
        boolean inQuotes = false; //we should not include possible comment, block symbols while in quotes
        CommentCharMap map = new CommentCharMap();
        Stream<String> stream = null;
        
        try {
            stream = Files.lines(Paths.get("/Users/asirimanna/Desktop/test.java"));
            String fileExtension = "java";
            String commentSymbol = map.getCommentMap().get(fileExtension);
            String blockStartSymbol = map.getStarterBlockMap().get(fileExtension);
            String blockEndSymbol = map.getEnderBlockMap().get(fileExtension);

            if(commentSymbol == null || blockStartSymbol == null || blockEndSymbol == null || commentSymbol.isEmpty() || blockStartSymbol.isEmpty() || blockEndSymbol.isEmpty()) {
                stream.close();
                throw new InvalidExtensionException("File Extension Not Supported");
            }
            List<String> lines = stream.collect(Collectors.toList());
            for(String line : lines) {
                for(int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    if(c == '"' || c == '\'') {
                        if(!inQuotes) { //if we are not in quotes, we enter quotes
                            inQuotes = true;
                        }
                        else {
                            inQuotes = false;
                        }
                    }
                    else if(!inQuotes) { //if we dont see a quote, we must add to comments but must take into account if we are quotes or not
                        if(c == commentSymbol.charAt(0) && (i + commentSymbol.length()) <= line.length() && line.substring(i, i+commentSymbol.length()).equals(commentSymbol)) { //if we see a single line comment
                            singleLineComments++;
                        }
                        else if(c == blockStartSymbol.charAt(0) && (i + blockStartSymbol.length()) <= line.length() && line.substring(i, i+blockStartSymbol.length()).equals(blockStartSymbol)) { //if we see the start of a block
                            totalCommentBlocks++;
                            inBlock = true;
                        }
                    } 
                }
                if(inBlock && !inQuotes) { //if we are in a block, we can only exit a block if we see 
                    totalLinesInCommentBlock++;
                    if(line.contains(blockEndSymbol)) { //if we are exiting the block
                        inBlock = false;
                    }
                }
                if(line.contains("TODO")) {
                    totalTodos++;
                }
                totalLines++;
            }
            System.out.println("Total # of lines: " + totalLines);
            System.out.println("Total # of comment lines: " + (singleLineComments + totalLinesInCommentBlock));
            System.out.println("Total # of single line comments: " + singleLineComments);
            System.out.println("Total # of comment lines within block comments: " + totalLinesInCommentBlock);
            System.out.println("Total # of block line comments: " + totalCommentBlocks);
            System.out.println("Total # of TODO's: " + totalTodos);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (InvalidExtensionException e) {
            e.printStackTrace();
        }
    }
}