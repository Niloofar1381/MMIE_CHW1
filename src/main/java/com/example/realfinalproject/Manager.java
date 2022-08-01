package com.example.realfinalproject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager {
    public static ArrayList<User> users=new ArrayList<>(0);
    public static ArrayList<BusinessUser>businessUsers=new ArrayList<>(0);
    public static ArrayList<Post> posts=new ArrayList<>(0);
    public static ArrayList<BusinessPost> businessPosts=new ArrayList<>(0);
    public static ArrayList<Message>messages=new ArrayList<>(0);
    public static ArrayList<Block>blocks=new ArrayList<>(0);
    public static ArrayList<Group> groups=new ArrayList<>(0);
    public static ArrayList<GroupMessage>groupMessages=new ArrayList<>(0);
    public Scanner sc=new Scanner(System.in);
    public boolean searchIds(String id)
    {
        for (User user : users) {
            if (user.getId().equals(id)){
                return true;
            }
        }
        return false;
    }
    public User matchIdAndPassword(String id,String password){
        for (User user : users) {
            if (user.getId().equals(id) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
    public User checkNationalCode(String Id,String nationalCode){
        for (User user : users) {
            if (user.getId().equals(Id) && user.getNationalCode().equals(nationalCode)){
                return user;
            }
        }
        return null;
    }
    public User findId(String id)
    {
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId().equals(id))
            {
                return users.get(i);
            }
        }
        return null;
    }
    public Post searchPostById(String Id){
        for (Post post : posts) {
            if (post.getId().equals(Id)){
                return post;
            }
        }
        return null;
    }
    public BusinessPost searchBusinessPostById(String Id){
        for (BusinessPost businessPost : businessPosts) {
            if (businessPost.getId().equals(Id)){
                return businessPost;
            }
        }
        return null;
    }
    public User checkLogin()
    {
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).isEntered())
            {
                return users.get(i);
            }
        }

        return null;
    }
    public Message searchMessage(int messageId){
        for (Message message : messages) {
            if (message.getId()==messageId){
                return message;
            }
        }
        return null;
    }
    public User postsUser(String postId)
    {
        for (int i = 0; i < posts.size(); i++) {
            if(posts.get(i).Id.equals(postId))
            {
                User user = findId(posts.get(i).userId);
                return user;
            }
        }
        return null;
    }
    public int getDistance(LocalDate first, LocalDate second){
        if (first.getYear()==second.getYear()){
            return second.getDayOfYear() - first.getYear();
        }
        else{
            int x= first.getDayOfYear();
            int y= second.getDayOfYear();
            return 365-x+y;
        }
    }
    public Group searchGroup(String groupId){
        for (Group group : groups) {
            if(group.getGroupId().equals(groupId)){
                return group;
            }
        }
        return null;
    }
    public GroupMessage findGroupMessage(String id)
    {
        for (int i = 0; i < groupMessages.size(); i++) {
            if(groupMessages.get(i).id.equals(id))
            {
                return groupMessages.get(i);
            }
        }
        return null;
    }
    public Message findMessageByTime(String time){
        for (int i = 0; i < messages.size(); i++) {
            if (messages.get(i).getTime().equals(time)){
                return messages.get(i);
            }
        }
        return null;
    }
    public GroupMessage findGroupMessageByTime(String time){
        for (int i = 0; i < groupMessages.size(); i++) {
            if (groupMessages.get(i).getTime().equals(time)){
                return groupMessages.get(i);
            }
        }
        return null;
    }
    //main functions
    public void register(String[] splitInput)
    {
        if (searchIds(splitInput[1]))
        {
            System.out.println("this id has been selected choose another id...");
        }
        else
        {
            if (splitInput[2].length()<8){
                System.out.println("choose another password...");
            }
            else{
                if (!splitInput[3].equals(splitInput[2])){
                    System.out.println("password is not valid");
                }
                else if(splitInput[5].equals("ordinary")){
                    if (!searchIds(splitInput[1])) {
                        User user = new User(splitInput[1], splitInput[2], splitInput[4], splitInput[5]);
                        users.add(user);
                        System.out.println("registered successfully");
                    }
                    else{
                        System.out.println("this id had been chosen before...");
                    }
                }
                else if (splitInput[5].equals("business")){
                    if (!searchIds(splitInput[1])) {
                        BusinessUser businessUser = new BusinessUser(splitInput[1], splitInput[2], splitInput[4], splitInput[5]);
                        businessUsers.add(businessUser);
                        users.add(businessUser);
                        System.out.println("registered successfully");
                    }
                    else
                    {
                        System.out.println("this id had been chosen before...");
                    }
                }
            }
        }
    }
    public String login(String[] splitInput)
    {
        String str="";
        if (searchIds(splitInput[1])){
            if (splitInput[2].equals("forgot")){
                if (checkNationalCode(splitInput[1],splitInput[3] )!=null){
                    str="logged in successfully";
                    checkNationalCode(splitInput[1],splitInput[3] ).setEntered(true);
                }
                else{
                    str="invalid national code...";
                }
            }
            else{
                if (matchIdAndPassword(splitInput[1], splitInput[2]) != null) {
                    matchIdAndPassword(splitInput[1], splitInput[2]).setEntered(true);
                    str="logged in successfully";
                }
                else {
                    str="Id and password not matched";
                }
            }
        }
        else{
            str="invalid Id";
        }
        return str;
    }
    public void showProfile(String[] splitInput)
    {
        if(findId(splitInput[1])==null)
        {
            System.out.println("Invalid id...");
        }
        else
        {
            User user=findId(splitInput[1]);
            System.out.println("Id: " + user.getId());
            System.out.println("followers numbers: " + user.getFollowerIds().size());
            System.out.println("following numbers: " + user.getFollowingIds().size());
            System.out.println("post numbers: " + user.getPostIds().size());
            if (!user.getFollowerIds().contains(checkLogin().getId())) {
                System.out.println("Do you want to follow this account?");
                String answer = sc.nextLine();
                if (answer.equals("Yes")) {
                    user.getFollowerIds().add(checkLogin().getId());
                    checkLogin().getFollowingIds().add(user.getId());
                    System.out.println("you are following " + user.getId());
                }
            }
        }
    }
    public void createPost(String answer)
    {
        boolean exist=true;
        if (checkLogin()==null){
            System.out.println("no one logged in...");
            exist=false;
        }
        if (exist)
        {
            User user1=checkLogin();
            if (user1.getBusinessAccount().equals("business")){
                BusinessUser businessUser=(BusinessUser) checkLogin();
                Integer num=Integer.parseInt(String.valueOf(businessUser.getPostIds().size()+1));
                String Id= businessUser.getId();
                Id+=num.toString();
                BusinessPost businessPost=new BusinessPost(businessUser.getId(),Id);
                businessPost.setId(Id);
                if(!answer.equals(""))
                {
                    businessPost.setText("ad   " + answer);
                }
                LocalDate time=LocalDate.now();
                businessPost.releasedTime=time;
                businessUser.getPostIds().add(businessPost.getId());
                businessPosts.add(businessPost);
                posts.add(businessPost);
                System.out.println("post created successfully");
            }
            else
            {
                User user=checkLogin();
                Integer num=Integer.parseInt(String.valueOf(user.getPostIds().size()+1));
                String Id=user.getId();
                Id+=num.toString();
                Post post=new Post(user.getId(),Id);
                post.setId(Id);
                if(!answer.equals(""))
                {
                    post.setText(answer);
                }
                user.getPostIds().add(post.getId());
                posts.add(post);
                System.out.println("post created successfully");
            }
        }
    }
    public void addComment(String postId,String comment)
    {
        boolean ordinaryPost=false,businessPost=false;
        if (searchPostById(postId)==null && searchBusinessPostById(postId)==null){
            System.out.println("This post doesn't exist...");
        }
        else if (searchPostById(postId)!=null && searchBusinessPostById(postId)==null){
            ordinaryPost=true;
        }
        else if (searchPostById(postId)!=null && searchBusinessPostById(postId)!=null){
            businessPost=true;
        }
        if (ordinaryPost){
            String id=searchPostById(postId).getId()+
                    (searchPostById(postId).commentsId.size()+1);
            Post post=new Post(checkLogin().getId(),id);
            post.setText(comment);
            searchPostById(postId).commentsId.add(post.getId());
            posts.add(post);
            System.out.println("comment added successfully");
        }
        else if (businessPost){
            String id=searchBusinessPostById(postId).getId()+
                    (searchPostById(postId).commentsId.size()+1);
            BusinessPost businessPost1=new BusinessPost(checkLogin().getId(),id);
            businessPost1.setText(comment);
            searchBusinessPostById(postId).commentsId.add(businessPost1.getId());
            businessPosts.add(businessPost1);
            posts.add(businessPost1);
            System.out.println("comment added successfully");
        }
    }
    public void like(String[] splitInput)
    {
        boolean ordinaryPost=false,businessPost=false;
        if (searchPostById(splitInput[1])==null && searchBusinessPostById(splitInput[1])==null){
            System.out.println("this post doesn't exist...");
        }
        else if (searchPostById(splitInput[1])!=null && searchBusinessPostById(splitInput[1])==null){
            ordinaryPost=true;
        }
        else if (searchPostById(splitInput[1])!=null && searchBusinessPostById(splitInput[1])!=null){
            businessPost=true;
        }
        if (ordinaryPost){
            searchPostById(splitInput[1]).likeUsersId.add(checkLogin().getId());
            System.out.println("liked successfully");
        }
        else if (businessPost){
            searchBusinessPostById(splitInput[1]).likeUsersId.add(checkLogin().getId());
            BusinessPost businessPost1 = searchBusinessPostById(splitInput[1]);
            LocalDate time = LocalDate.now();
            User user = checkLogin();
            businessPost1.likes.put(user, time);
            businessPost1.likesUsersForTable.add(user.getId());
            businessPost1.likesLocalDateForTable.add(time);
            System.out.println("liked successfully");
        }
    }
    public void showComments(String[] splitInput)
    {
        if(postsUser(splitInput[2]).getId().equals(checkLogin().getId())) {
            if (searchPostById(splitInput[2]) == null) {
                System.out.println("this post does not exist");
            } else {
                Post post = searchPostById(splitInput[2]);
                System.out.println("The number of comments: " + post.commentsId.size());
                for (int i = 0; i < post.commentsId.size(); i++) {
                    System.out.println(searchPostById(post.commentsId.get(i)).text);
                }
            }
        }
        else
        {
            System.out.println("This post doesn't exist for you...");
        }
    }
    public void showLikes(String[] splitInput)
    {
        if(postsUser(splitInput[2]).getId().equals(checkLogin().getId())) {
            if (searchPostById(splitInput[2]) == null) {
                System.out.println("this post does not exist");
            } else {
                Post post = searchPostById(splitInput[2]);
                System.out.println("The number of Likes: " + post.likeUsersId.size());
                for (int i = 0; i < post.likeUsersId.size(); i++) {
                    System.out.println(post.likeUsersId.get(i));
                }
            }
        }
        else
        {
            System.out.println("This post doesn't exist for you...");
        }
    }
    public void showMainPage()
    {
        if (checkLogin()==null){
            System.out.println("no one logged in...");
        }
        else {
            User user = checkLogin();
            System.out.println("user's posts: ");
            user.printUserPosts();
            System.out.println("----------");
            System.out.println("follower's posts: ");
            user.printFollowersPosts();
            System.out.println("----------");
            System.out.println("following's posts: ");
            user.printFollowingsPosts();
            for (int i = 0; i < user.getFollowerIds().size(); i++) {
                User user1 = findId(user.getFollowerIds().get(i));
                if (user1 instanceof BusinessUser) {
                    int n = user1.getPostIds().size();
                    BusinessUser businessUser=(BusinessUser) user1;
                    if(n>5) {
                        for (int i1 = n - 1; i1 > n - 6; i1--) {
                            Post post = searchPostById(businessUser.getPostIds().get(i1));
                            BusinessPost businessPost=(BusinessPost) post;
                            businessPost.getViewers().add(checkLogin().getId());
                            businessPost.views.put(checkLogin(),LocalDate.now());
                            businessPost.viewUsersForTable.add(checkLogin().getId());
                            businessPost.viewLocalDatesForTable.add(LocalDate.now());
                        }
                    }
                    else
                    {
                        for(int i1=0;i1<n;i1++)
                        {
                            Post post = searchPostById(businessUser.getPostIds().get(i1));
                            BusinessPost businessPost=(BusinessPost) post;
                            businessPost.getViewers().add(checkLogin().getId());
                            businessPost.views.put(checkLogin(),LocalDate.now());
                        }
                    }
                }
            }
            for (int i = 0; i < user.getFollowingIds().size(); i++) {
                User user1 = findId(user.getFollowingIds().get(i));
                if (user1 instanceof BusinessUser) {
                    int n=user1.getPostIds().size();
                    BusinessUser businessUser=(BusinessUser) user1;
                    if(n>5) {
                        for (int i1 = n - 1; i1 > n - 6; i1--) {
                            Post post = searchPostById(businessUser.getPostIds().get(i1));
                            BusinessPost businessPost=(BusinessPost) post;
                            businessPost.getViewers().add(checkLogin().getId());
                            businessPost.views.put(checkLogin(),LocalDate.now());
                        }
                    }
                    else
                    {
                        for(int i1=0;i1<n;i1++)
                        {
                            Post post = searchPostById(businessUser.getPostIds().get(i1));
                            BusinessPost businessPost=(BusinessPost) post;
                            businessPost.getViewers().add(checkLogin().getId());
                            businessPost.views.put(checkLogin(),LocalDate.now());
                        }
                    }

                }
            }

        }
    }
    public void showStats(String[] splitInput)
    {
        LocalDate now=LocalDate.now();
        User user = checkLogin();
        if(user instanceof BusinessUser)
        {
            BusinessPost businessPost=searchBusinessPostById(splitInput[2]);
            int distance=getDistance(businessPost.releasedTime,now);
            for (int i=0;i<=distance;i++){
                int num=0;
                for (LocalDate value : businessPost.likes.values()) {
                    if (getDistance(businessPost.releasedTime,value)==i){
                        num++;
                    }
                }
                System.out.println("the number of likes in day" + i +": " + num);
            }
            for (int i=0;i<=distance;i++){
                int num=0;
                for (LocalDate value : businessPost.views.values()) {
                    if (getDistance(businessPost.releasedTime,value)==i){
                        num++;
                    }
                }
                System.out.println("the number of views in day" + i +": " + num);
            }
        }
        else
        {
            System.out.println("you are not a business user...");
        }
    }
    public void startPrivateMessage(User sender,User receiver,String text)
    {
        boolean blocked=false;
        if(receiver==null)
        {
            System.out.println("Invalid id...");
        }
        else{
            User user=receiver;
            for (Block block : blocks)
            {
                if (block.getBlocked().equals(sender) && block.getBlocker().equals(receiver)){
                    blocked=true;
                    break;
                }
            }
        }
        if (!blocked)
        {
            int num=0;
            for (int i = 0; i < messages.size(); i++) {
                num++;
            }
            Message message=new Message(sender,receiver,num+1,text,LocalDate.now());
            message.localDateToString=LocalDate.now().toString();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            message.setTime(dtf.format(now));
            sender.getMessageIds().add(message.getId());
            messages.add(message);
            System.out.println(message.getText());
        }
        else
        {
            System.out.println(receiver.getId() + " blocked you...");
        }
    }
    public void forwardMessage(String userId,int messageId)
    {
        if (findId(userId)==null){
            System.out.println("user doesn't exist...");
        }
        if (searchMessage(messageId)==null)
        {
            System.out.println("this messageId doesn't exist...");
        }
        else if (findId(userId)!=null && searchMessage(messageId)!=null)
        {
            boolean blocked=false;
            for (Block block : blocks)
            {
                if (block.getBlocked().equals(checkLogin()) && block.getBlocker().equals(findId(userId))){
                    blocked=true;
                    break;
                }
            }
            if(!blocked)
            {
                if (searchMessage(messageId).getSender().equals(checkLogin()) ||
                        searchMessage(messageId).getReceiver().equals(checkLogin())) {
                    int id = 0;
                    Message message = searchMessage(messageId);
                    User user = findId(userId);
                    for (int i = 0; i < messages.size(); i++) {
                        id++;
                    }
                    Message forwardedMessage = new Message(checkLogin(), user, id + 1, "forwarded from " + message.getSender().getId() + "\n" + message.getText(),LocalDate.now());
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    forwardedMessage.setTime(dtf.format(now));
                    forwardedMessage.localDateToString=LocalDate.now().toString();
                    forwardedMessage.forwarded = true;
                    messages.add(forwardedMessage);
                    checkLogin().getMessageIds().add(forwardedMessage.getId());
                    System.out.println(forwardedMessage.getText());
                }
            }
            else
            {
                System.out.println(findId(userId).getId() + " blocked you...");
            }
        }
    }
    public void replyMessage(int id,String text)
    {
        if (searchMessage(id)==null)
        {
            System.out.println("this messageId doesn't exist...");
        }
        else
        {
            boolean blocked=false;
            for (Block block : blocks)
            {
                User user=searchMessage(id).getSender();
                if (block.getBlocked().equals(checkLogin()) && block.getBlocker().equals(user)){
                    blocked=true;
                    break;
                }
            }
            if (!blocked)
            {
                if (searchMessage(id).getSender().equals(checkLogin()) ||
                        searchMessage(id).getReceiver().equals(checkLogin())) {

                    Message message = searchMessage(id);
                    String charsOfMessage = message.getText().substring(0, message.getText().length()-3);
                    int id1 = 0;
                    User sender = checkLogin();
                    User receiver = message.getReceiver();
                    if (sender.equals(receiver)) {
                        receiver = message.getSender();
                    }
                    for (int i = 0; i < messages.size(); i++) {
                        id1++;
                    }
                    Message repliedMessage = new Message(sender, receiver, id1 + 1, "replied:" + charsOfMessage + "\n" + text,LocalDate.now());
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    repliedMessage.setTime(dtf.format(now));
                    repliedMessage.localDateToString=LocalDate.now().toString();
                    messages.add(repliedMessage);
                    sender.getMessageIds().add(repliedMessage.getId());
                    System.out.println(repliedMessage.getText());
                }
            }
            else
            {
                User user=searchMessage(id).getSender();
                System.out.println(user.getId() + " blocked you...");
            }
        }
    }
    public void block(String userId)
    {
        if (findId(userId)==null){
            System.out.println("user doesn't exist...");
        }
        else{
            User user=findId(userId);
            Block block=new Block(checkLogin(),user);
            block.setBlocked(user);
            block.setBlocker(checkLogin());
            blocks.add(block);
            System.out.println(user.getId() + " is blocked successfully");
        }
    }
    public void editMessage(int id,String text)
    {
        if (searchMessage(id)==null)
        {
            System.out.println("this messageId doesn't exist...");
        }
        else{
            Message message=searchMessage(id);
            boolean blocked=false;
            for (Block block : blocks) {
                if (block.getBlocked().equals(checkLogin()) && block.getBlocker().equals(message.getReceiver())){
                    blocked=true;
                    break;
                }
            }
            if (!blocked)
            {
                if (message.getSender().equals(checkLogin()))
                {
                    int index = checkLogin().indexOfMessage(message), all = checkLogin().getMessageIds().size();
                    if (index <= all && index > all - 10 && !message.forwarded) {
                        message.setText(text);
                        System.out.println("message edited successfully");
                    } else {
                        System.out.println("sth went wrong...");
                    }
                } else
                {
                    System.out.println("you cannot edit this message...");
                }
            }
            else
            {
                System.out.println("you are blocked...");
            }
        }
    }
    public void showChats(String[] splitInput)
    {
        ArrayList<Message> selectedMessages=new ArrayList<>();
        ArrayList<User> selectedUsers=new ArrayList<>();
        if (findId(splitInput[2])==null){
            System.out.println("this user doesn't exist...");
        }
        else{
            boolean exist=false;
            User user=findId(splitInput[2]);
            for (Message message : messages)
            {
                if ((message.getReceiver().equals(checkLogin()) && message.getSender().equals(user)))
                {
                    exist=true;
                    selectedMessages.add(message);
                    selectedUsers.add(user);
                }
                else if ((message.getSender().equals(checkLogin()) && message.getReceiver().equals(user)))
                {
                    exist=true;
                    selectedMessages.add(message);
                    selectedUsers.add(checkLogin());
                }
            }
            if (exist) {
                for (int i = 0; i < selectedMessages.size(); i++) {
                    System.out.println(selectedUsers.get(i).getId() + ": " +  selectedMessages.get(i).getText());
                }
            }
            else {
                System.out.println("there isn't any chat...");
            }
        }
    }
    public void createGroup(String name,String id)
    {
        if (searchGroup(id)==null)
        {
            Group group = new Group(checkLogin(), name, id);
            groups.add(group);
            group.setAdmin(checkLogin());
            System.out.println("new group created successfully");
        }
        else{
            System.out.println("this id had been selected before...");
        }
    }
    public void addUser(String[] splitInput)
    {
        if (findId(splitInput[2])==null){
            System.out.println("this user doesn't exist...");
        }
        else if (searchGroup((splitInput[3]))!=null){
            if(searchGroup((splitInput[3])).getAdmin().equals(checkLogin())){
                User user=findId(splitInput[2]);
                Group group=searchGroup((splitInput[3]));
                if(!group.getUsers().contains(user)) {
                    group.getUsers().add(user);
                    group.getBanned().add(false);
                    System.out.println("added successfully");
                    for (int i = 0; i < group.groupMessages.size(); i++) {
                       group.groupMessages.get(i).getSeen().add(false);
                    }
                }
                else{
                    System.out.println("you were added to the group before...");
                }
            }
            else{
                System.out.println("you aren't admin...");
            }
        }
        else{
            System.out.println("this group doesn't exist...");
        }
    }
    public String changeGroupName(String[] splitInput)
    {
        String string = "";
        if (searchGroup((splitInput[2]))==null){
            string = "this group doesn't exist...";
        }
        else{
            if(searchGroup((splitInput[2])).getAdmin().equals(checkLogin())) {
                Group group = searchGroup((splitInput[2]));
                group.setGroupName(splitInput[3]);
                string = "group name is changed successfully";
            }
            else{
                string = "you aren't admin...";
            }
        }
        return string;
    }
    public String changeGroupId(String[] splitInput)
    {
        String string = "";
        if (searchGroup((splitInput[2]))==null){
            string = "this group doesn't exist...";
        }
        else{
            if(searchGroup((splitInput[2])).getAdmin().equals(checkLogin())) {
                if (searchGroup(splitInput[3])==null) {
                    Group group = searchGroup((splitInput[2]));
                    group.setGroupId(splitInput[3]);
                    string = "group id is changed successfully";
                    for (int i=0;i<group.getGroupMessages().size();i++){
                        group.getGroupMessages().get(i).groupId = splitInput[3];
                    }
                }
                else
                {
                    string = "this group id had been chosen before...";
                }
            }
            else{
                string = "you aren't admin...";
            }
        }
        return string;
    }
    public String removeUser(String[] splitInput)
    {
        String string = "";
        if (searchGroup((splitInput[2]))==null){
            string = "this group doesn't exist...";
        }
        else{
            if(searchGroup((splitInput[2])).getAdmin().equals(checkLogin())) {
                if (findId(splitInput[3])==null){
                    string = "this user doesn't exist...";
                }
                else{
                    User user=findId(splitInput[3]);
                    boolean bool=false;
                    Group group=searchGroup((splitInput[2]));
                    int k=0;
                    for (int i = 0; i < group.getUsers().size(); i++) {
                        if(group.getUsers().get(i).getId().equals(splitInput[3]))
                        {
                            k=i;
                            break;
                        }
                    }
                    for (int i = 0; i < group.groupMessages.size(); i++) {
                        group.groupMessages.get(i).getSeen().remove(k);
                    }
                    for (int i=0;i<group.getUsers().size();i++) {
                        if (group.getUsers().get(i).equals(user)){
                            group.getUsers().remove(group.getUsers().get(i));
                            group.getBanned().remove(i);
                            string = "user removed successfully";
                            bool=true;
                            break;
                        }
                    }
                    if (!bool){
                        string = "this user wasn't the member of this group...";
                    }
                }
            }
            else{
                string = "you aren't admin...";
            }
        }
        return string;
    }
    public String banUser(String[] splitInput)
    {
        String string = "";
        if (searchGroup((splitInput[2]))==null){
            string = "this group doesn't exist...";
        }
        else{
            if(searchGroup((splitInput[2])).getAdmin().equals(checkLogin())) {
                if (findId(splitInput[3])==null){
                    string = "this user doesn't exist...";
                }
                else{
                    User user=findId(splitInput[3]);
                    boolean bool=false;
                    Group group=searchGroup((splitInput[2]));
                    for (int i = 0; i < group.getUsers().size(); i++) {
                        if (group.getUsers().get(i).equals(user)) {
                            bool = true;
                            group.getBanned().set(i,true);
                            string = "user banned successfully";
                            break;
                        }
                    }
                    if(!bool)
                    {
                        string = "this user wasn't the member of this group...";
                    }
                }
            }
            else{
                string = "you aren't admin...";
            }
        }
        return string;
    }
    public void sendGroupMessage(String[] splitInput,String text)
    {
        boolean bool=false;
        if (searchGroup((splitInput[4]))==null)
        {
            System.out.println("this group doesn't exist...");
        }
        else
        {
            Group group=searchGroup(splitInput[4]);
            boolean admin=false;
            if (checkLogin().equals(group.getAdmin()))
            {
                admin=true;
                int n=group.groupMessages.size()+1;
                String str=Integer.toString(n);
                String id = group.groupId + str;
                GroupMessage groupMessage = new GroupMessage(checkLogin(), text, id, group.groupId,LocalDate.now());
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                groupMessage.setTime(dtf.format(now));
                groupMessage.getSeen().add(false);
                for (int i = 0; i < group.getUsers().size(); i++) {
                    groupMessage.getSeen().add(false);
                }
                groupMessage.localDateToString = LocalDate.now().toString();
                groupMessages.add(groupMessage);
                group.getGroupMessages().add(groupMessage);
                System.out.println("message is sent to the group successfully");
            }
            if (!admin)
            {
                for (int i = 0; i < group.getUsers().size(); i++)
                {
                    if (group.getUsers().get(i).equals(checkLogin()) && group.getBanned().get(i).equals(false)) {
                        bool = true;
                        int n=group.groupMessages.size()+1;
                        String str=Integer.toString(n);
                        String id = group.groupId + str;
                        GroupMessage groupMessage = new GroupMessage(checkLogin(), text, id, group.groupId,LocalDate.now());
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                        LocalDateTime now = LocalDateTime.now();
                        groupMessage.setTime(dtf.format(now));
                        groupMessage.getSeen().add(false);
                        for (int j = 0; j < group.getUsers().size(); j++) {
                            groupMessage.getSeen().add(false);
                        }
                        groupMessage.localDateToString = LocalDate.now().toString();
                        groupMessages.add(groupMessage);
                        group.getGroupMessages().add(groupMessage);
                        System.out.println("message is sent to the group successfully");
                        break;
                    }
                }
            }
            if(!bool && !admin)
            {
                System.out.println("You can't send message to this group.");
            }
        }
    }
    public void editGroupMessage(String groupMessageId,String newText)
    {
        boolean bool=false;
        if(findGroupMessage(groupMessageId)==null)
        {
            System.out.println("This message doesn't exist...");
        }
        else
        {
            boolean admin=false;
            GroupMessage groupMessage=findGroupMessage(groupMessageId);
            String groupId=groupMessage.groupId;
            Group group = searchGroup(groupId);
            if (checkLogin().equals(group.getAdmin())){
                admin=true;
                groupMessage.setText(newText);
                System.out.println("message is edited successfully");
            }
            if (!admin)
            {
                for (int i = 0; i < group.getUsers().size(); i++) {
                    if (group.getUsers().get(i).equals(checkLogin()) && group.getBanned().get(i).equals(false)) {
                        bool = true;
                        groupMessage.setText(newText);
                        System.out.println("message is edited successfully");
                        break;
                    }
                }
            }
            if(!bool && !admin)
            {
                System.out.println("You can't edit message in this group.");
            }

        }
    }
    public void replyGroupMessage(String groupMessageId,String text)
    {
        boolean bool=false;
        if(findGroupMessage(groupMessageId)==null)
        {
            System.out.println("This message doesn't exist...");
        }
        else
        {
            boolean admin=false;
            GroupMessage groupMessage = findGroupMessage(groupMessageId);
            String groupId = groupMessage.groupId;
            Group group = searchGroup(groupId);
            if (checkLogin().equals(group.getAdmin()))
            {
                admin=true;
                String charsOfMessage = groupMessage.getText().substring(0,4);
                int n = group.groupMessages.size()+1;
                String str=Integer.toString(n);
                String id=group.groupId + str;
                GroupMessage groupMessage1=new GroupMessage(checkLogin(),"replied "+charsOfMessage + "\n" + text,id,groupId,LocalDate.now());
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                groupMessage1.setTime(dtf.format(now));
                groupMessage1.getSeen().add(false);
                for (int i = 0; i < group.getUsers().size(); i++) {
                    groupMessage1.getSeen().add(false);
                }
                groupMessage1.localDateToString = LocalDate.now().toString();
                groupMessages.add(groupMessage1);
                group.groupMessages.add(groupMessage1);
                System.out.println("message is replied successfully");
            }
            if (!admin)
            {
                for (int i = 0; i < group.getUsers().size(); i++)
                {
                    if (group.getUsers().get(i).equals(checkLogin()) && group.getBanned().get(i).equals(false)) {
                        bool = true;
                        String charsOfMessage = groupMessage.getText().substring(0, 4);
                        int n = group.groupMessages.size()+1;
                        String str=Integer.toString(n);
                        String id=group.groupId + str;
                        GroupMessage groupMessage1=new GroupMessage(checkLogin(),"replied "+charsOfMessage + "\n" + text,id,group.groupId,LocalDate.now());
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                        LocalDateTime now = LocalDateTime.now();
                        groupMessage1.setTime(dtf.format(now));
                        groupMessage1.localDateToString = LocalDate.now().toString();
                        groupMessage1.getSeen().add(false);
                        for (int j = 0; j < group.getUsers().size(); j++) {
                            groupMessage1.getSeen().add(false);
                        }
                        groupMessages.add(groupMessage1);
                        group.groupMessages.add(groupMessage1);
                        System.out.println("message is replied successfully");
                        break;
                    }
                }
            }
            if(!bool && !admin)
            {
                System.out.println("You can't send message in this group.");
            }
        }
    }
    public void forwardGroupMessage(String[] splitInput)
    {
        boolean bool=false;

        if (searchGroup((splitInput[5]))==null){
            System.out.println("this group doesn't exist...");
        }
        else {
            if (findGroupMessage(splitInput[4]) == null) {
                System.out.println("This message doesn't exist...");
            }
            else {
                boolean admin=false;
                Group group=searchGroup(splitInput[5]);
                if (group.getAdmin().equals(checkLogin()))
                {
                    admin=true;
                    GroupMessage groupMessage = findGroupMessage(splitInput[4]);
                    String id = group.getGroupId() + group.getGroupMessages().size() + 1;
                    String text="Forwarded from " +groupMessage.getSender().getId() +"\n" + groupMessage.getText();
                    GroupMessage groupMessage1 = new GroupMessage(checkLogin(), text, id, group.groupId,LocalDate.now());
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    groupMessage1.setTime(dtf.format(now));
                    groupMessage1.localDateToString = LocalDate.now().toString();
                    groupMessage1.getSeen().add(false);
                    for (int i = 0; i < group.getUsers().size(); i++) {
                        groupMessage1.getSeen().add(false);
                    }
                    groupMessages.add(groupMessage1);
                    group.getGroupMessages().add(groupMessage1);
                    System.out.println("groupMessage forwarded to the group successfully");
                }
                if (!admin)
                {
                    for (int i = 0; i < group.getUsers().size(); i++) {
                        if (group.getUsers().get(i).equals(checkLogin()) && group.getBanned().get(i).equals(false)) {
                            bool = true;
                            GroupMessage groupMessage = findGroupMessage(splitInput[4]);
                            String id = group.getGroupId() + group.getGroupMessages().size() + 1;
                            String text = "Forwarded from " +groupMessage.getSender().getId() +"\n" + groupMessage.getText();
                            GroupMessage groupMessage1 = new GroupMessage(checkLogin(), text, id, group.groupId, LocalDate.now());
                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                            LocalDateTime now = LocalDateTime.now();
                            groupMessage1.setTime(dtf.format(now));
                            groupMessage1.localDateToString = LocalDate.now().toString();
                            groupMessage1.getSeen().add(false);
                            for (int j = 0; j < group.getUsers().size(); j++) {
                                groupMessage1.getSeen().add(false);
                            }
                            groupMessages.add(groupMessage1);
                            group.getGroupMessages().add(groupMessage1);
                            System.out.println("groupMessage forwarded to the group successfully");
                        }
                    }
                }
                if(!bool && !admin)
                {
                    System.out.println("You can't send message in this group.");
                }
            }
        }

    }
    public void showGroupMessages(String[] splitInput)
    {
        boolean bool = false;
        if (searchGroup((splitInput[3])) == null) {
            System.out.println("this group doesn't exist...");
        }
        else
        {
            Group group=searchGroup(splitInput[3]);
            if (group.getUsers().contains(checkLogin()) || group.getAdmin().equals(checkLogin())){
                bool=true;
            }
            if (bool) {
                for (GroupMessage groupMessage : group.getGroupMessages()) {
                    System.out.print(groupMessage.getSender().getId() + " : ");
                    System.out.println(groupMessage.getText());
                }
            }
            else{
                System.out.println("you aren't member of this group...");
            }
        }
    }
    public String forwardGroupToPv(String[] splitInput)
    {
        String str="";
        if (searchGroup((splitInput[4])) == null) {
            str="this group doesn't exist...";
        }
        else if (findId(splitInput[5])==null){
            str="this user doesn't exist...";
        }
        else if (findGroupMessage(splitInput[6])==null){
            str="this groupMessage doesn't exist...";
        }
        else{
            boolean blocked=false;
            User receiver=findId(splitInput[5]);
            for (Block block : blocks)
            {
                if (block.getBlocker().equals(receiver) && block.getBlocked().equals(checkLogin())){
                    blocked=true;
                    break;
                }
            }
            if (!blocked)
            {
                GroupMessage groupMessage = findGroupMessage(splitInput[6]);
                int num = messages.size() + 1;
                Message message = new Message(checkLogin(), receiver, num, "Forwarded from "+groupMessage.getSender().getId()+"\n" + groupMessage.getText(), LocalDate.now());
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                message.setTime(dtf.format(now));
                message.forwarded=true;
                message.localDateToString=LocalDate.now().toString();
                messages.add(message);
                str="groupMessage forwarded successfully";
            }
            else
            {
                str="you are blocked...";
            }
        }
        return str;
    }
    public String forwardPvToGroup(String[] splitInput)
    {
        String string="";
        if (searchGroup((splitInput[4])) == null) {
           string="this group doesn't exist...";
        }
        else if (searchMessage(Integer.parseInt(splitInput[5]))==null){
            string="this messageId doesn't exist...";
        }
        else{
            boolean banned=false;
            Group group=searchGroup((splitInput[4]));
            for (int i = 0; i < group.getUsers().size(); i++)
            {
                if (group.getBanned().get(i).equals(true) && group.getUsers().get(i).equals(checkLogin())){
                    banned=true;
                    break;
                }
            }
            if (!banned)
            {
                Message message = searchMessage(Integer.parseInt(splitInput[5]));
                int num = group.groupMessages.size() + 1;
                String str = Integer.toString(num);
                String id = group.getGroupId() + str;
                System.out.println(id);
                GroupMessage groupMessage = new GroupMessage(checkLogin(), "Forwarded from "+message.getSender().getId()+ "\n" + message.getText(), id, group.groupId, LocalDate.now());
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                groupMessage.setTime(dtf.format(now));
                groupMessage.getSeen().add(false);
                for (int i = 0; i < group.getUsers().size(); i++) {
                    groupMessage.getSeen().add(false);
                }
                groupMessage.localDateToString = LocalDate.now().toString();
                group.groupMessages.add(groupMessage);
                groupMessages.add(groupMessage);
                string="message forwarded successfully to the group";
            }
            else{
                string="you are banned...";
            }
        }
        return string;
    }
    public ArrayList<Message> searchTextMessage(String text)
    {
        ArrayList<Message> foundMessages=new ArrayList<>();
        for (Message message : messages) {
            if (message.getText().contains(text)&&(message.getSender().equals(checkLogin())||message.getReceiver().equals(checkLogin()))){
                foundMessages.add(message);
            }
        }

        /*for (Message foundMessage : foundMessages) {
            System.out.println("Id: " + foundMessage.getId());
            System.out.println("date: " + foundMessage.getLocalDate());
            String str=foundMessage.getText().substring(0,10);
            System.out.println("some characters of message: " + str);
        }*/
        return foundMessages;
    }
    public ArrayList<GroupMessage> searchTextGroupMessage(String text)
    {
        ArrayList<GroupMessage> foundGroupMessages=new ArrayList<>();
        for (GroupMessage groupMessage : groupMessages) {
            if (groupMessage.getText().contains(text)&&(searchGroup(groupMessage.groupId).getUsers().contains(checkLogin())||
                    searchGroup(groupMessage.groupId).getAdmin().equals(checkLogin()))){
                foundGroupMessages.add(groupMessage);
            }
        }
        /*for (GroupMessage foundGroupMessage : foundGroupMessages)
        {
            System.out.println("Id: " + foundGroupMessage.getId());
            System.out.println("date: " + foundGroupMessage.getLocalDate());
            String str=foundGroupMessage.getText().substring(0,10);
            System.out.println("some characters of message: " + str);
        }*/
        return foundGroupMessages;
    }
    public void showPosts(String[] splitInput)
    {
        User user=findId(splitInput[1]);
        System.out.println(user.getId()+" posts:");
        for (int i = 0; i < user.getPostIds().size(); i++) {
            System.out.println(searchPostById(user.getPostIds().get(i)).getText());
            if(user instanceof BusinessUser)
            {
                BusinessPost businessPost=(BusinessPost)searchBusinessPostById(user.getPostIds().get(i));
                businessPost.getViewers().add(checkLogin().getId());
                LocalDate localDate=LocalDate.now();
                businessPost.views.put(checkLogin(),localDate);
            }
        }
    }
    public void setFriends()
    {
        for (User user : users) {
            user.allFriendIds.addAll(user.getFollowerIds());
            user.allFriendIds.addAll(user.getFollowingIds());
        }
    }
    public ArrayList<User> suggestFriend()
    {
        User user=checkLogin();
        setFriends();
        ArrayList<User> userNewFriends=new ArrayList<>();
        for (int i = 0; i < user.getAllFriendIds().size(); i++) {
            ArrayList<User> newFriends=new ArrayList<>();
            for (int i1 = 0; i1 < findId(user.getAllFriendIds().get(i)).getAllFriendIds().size(); i1++) {
                if (!user.getAllFriendIds().contains(findId(user.getAllFriendIds().get(i)).getAllFriendIds().get(i1))){
                    newFriends.add(findId(findId(user.getAllFriendIds().get(i)).getAllFriendIds().get(i1)));
                }
            }
            userNewFriends.addAll(newFriends);
        }
        ArrayList<User>notFriendsWithOurFriends=new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            if(!user.getAllFriendIds().contains(users.get(i).getId())&&!users.get(i).equals(user)
                    &&!userNewFriends.contains(users.get(i)))
            {
                notFriendsWithOurFriends.add(users.get(i));
            }
        }
        ArrayList<Integer>gradeForNotFriends=new ArrayList<>();
        for (int i = 0; i < notFriendsWithOurFriends.size(); i++) {
            int num=0;
            for (int i1 = 0; i1 < notFriendsWithOurFriends.get(i).getPostIds().size(); i1++) {
                if( searchPostById(notFriendsWithOurFriends.get(i).getPostIds().get(i1)).likeUsersId.contains(user.getId()))
                {
                    num++;
                }
            }
            gradeForNotFriends.add(num);
        }
        int max1=gradeForNotFriends.get(0);
        int k1=0;
        for (int i = 0; i < gradeForNotFriends.size(); i++) {
            if(gradeForNotFriends.get(i)>max1)
            {
                max1=gradeForNotFriends.get(i);
                k1=i;
            }
        }
        System.out.println("Recommended friend for you\n"+notFriendsWithOurFriends.get(k1).getId());
        ArrayList<Integer>commonFriendsAndLikePosts=new ArrayList<>();
        for (int i = 0; i < userNewFriends.size(); i++) {
            int num=0;
            for (int i1 = 0; i1 <userNewFriends.get(i).getAllFriendIds().size(); i1++) {
                if(user.getAllFriendIds().contains(userNewFriends.get(i).getAllFriendIds().get(i1)))
                {
                    num++;
                }
            }
            for (int i1 = 0; i1 < userNewFriends.get(i).getPostIds().size(); i1++) {
                if( searchPostById(userNewFriends.get(i).getPostIds().get(i1)).likeUsersId.contains(user.getId()))
                {
                    num++;
                }
            }
            commonFriendsAndLikePosts.add(num);
        }
        int max2=commonFriendsAndLikePosts.get(0);
        int k2=0;
        for (int i = 0; i < commonFriendsAndLikePosts.size(); i++) {
            if(commonFriendsAndLikePosts.get(i)>max2)
            {
                max2=commonFriendsAndLikePosts.get(i);
                k2=i;
            }
        }
        System.out.println("Recommended friend for you\n"+userNewFriends.get(k2).getId());
        ArrayList<User>suggestUsers=new ArrayList<>();
        suggestUsers.add(notFriendsWithOurFriends.get(k1));
        suggestUsers.add(userNewFriends.get(k2));
        return suggestUsers;
    }
    public void setFavoriteNumber()
    {
        for (int i = 0; i < businessPosts.size(); i++) {
            for (int i1 = 0; i1 < users.size(); i1++) {
                if(businessPosts.get(i).getLikeUsersId().contains(users.get(i1).getId()))
                {
                    businessPosts.get(i).favoriteNumbers.put(users.get(i1),1.0);
                    businessPosts.get(i).favoriteNumberUser.add(users.get(i1).getId());
                    businessPosts.get(i).favoriteNumberDouble.add(1.0);
                }
                else if(businessPosts.get(i).getViewers().contains(users.get(i1).getId()))
                {
                    businessPosts.get(i).favoriteNumbers.put(users.get(i1),0.0);
                    businessPosts.get(i).favoriteNumberUser.add(users.get(i1).getId());
                    businessPosts.get(i).favoriteNumberDouble.add(0.0);
                }
                else
                {
                    businessPosts.get(i).favoriteNumbers.put(users.get(i1),0.5);
                    businessPosts.get(i).favoriteNumberUser.add(users.get(i1).getId());
                    businessPosts.get(i).favoriteNumberDouble.add(0.5);
                }
            }
        }
    }
    public BusinessPost suggestBusinessPost()
    {
        setFavoriteNumber();
        ArrayList<BusinessPost> notViewedPosts=new ArrayList<>();
        ArrayList<BusinessPost>likedPosts=new ArrayList<>();
        ArrayList<Double>favoritism=new ArrayList<>();
        for (BusinessPost post : businessPosts) {
            if (!post.getViewers().contains(checkLogin().getId())) {
                notViewedPosts.add(post);
            }
        }
        for (BusinessPost businessPost : businessPosts) {
            if (businessPost.getLikeUsersId().contains(checkLogin().getId())) {
                likedPosts.add(businessPost);
            }
        }

        for (int j = 0; j < notViewedPosts.size(); j++) {
            double num=0.0;
            for (int k = 0; k < likedPosts.size(); k++) {
                for (int i = 0; i < users.size(); i++) {
                    num+= Math.abs(notViewedPosts.get(j).favoriteNumbers.get(users.get(i))
                            -likedPosts.get(k).favoriteNumbers.get(users.get(i)));
                }
            }
            favoritism.add(num);
        }
        double min=favoritism.get(0);
        int k=0;
        for (int i = 0; i < favoritism.size(); i++) {
            if(favoritism.get(i)<min)
            {
                min=favoritism.get(i);
                k=i;
            }
        }
        System.out.println("Recommended advertisement:\n" + notViewedPosts.get(k).getText()+"\n"+
                notViewedPosts.get(k).userId);
        return notViewedPosts.get(k);
    }
    public void logout()
    {
        checkLogin().setEntered(false);
        System.out.println("logged out successfully");
    }
}