import java.util.HashMap;

class CommentCharMap {
        private HashMap<String, String> extensionToComment = new HashMap<>();
        private HashMap<String, String> extensionToBlockStarter = new HashMap<>();
        private HashMap<String, String> extensionToBlockEnder = new HashMap<>();


        CommentCharMap() {
            extensionToComment.put("java", "//");
            extensionToComment.put("cpp", "//");
            extensionToComment.put("c", "//");
            extensionToComment.put("python", "#");
            extensionToBlockStarter.put("java", "/*");
            extensionToBlockStarter.put("cpp", "/*");
            extensionToBlockStarter.put("c", "/*");
            extensionToBlockStarter.put("python", "\"\"\"");
            extensionToBlockEnder.put("java", "*/");
            extensionToBlockEnder.put("cpp", "*/");
            extensionToBlockEnder.put("c", "*/");
            extensionToBlockEnder.put("python", "\"\"\"");
        }

        public HashMap<String, String> getCommentMap() {
            return extensionToComment;
        }

        public HashMap<String, String> getStarterBlockMap() {
            return extensionToBlockStarter;
        }

        public HashMap<String, String> getEnderBlockMap() {
            return extensionToBlockEnder;
        }
}