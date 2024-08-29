package quizMaster.quizMaster.business.concretes;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quizMaster.quizMaster.business.abstracts.QuestionService;
import quizMaster.quizMaster.core.dataAccess.UserDao;
import quizMaster.quizMaster.core.entities.User;
import quizMaster.quizMaster.core.enums.Categories;
import quizMaster.quizMaster.core.enums.Options;
import quizMaster.quizMaster.core.utilities.results.Result;
import quizMaster.quizMaster.core.utilities.services.ChatGPTService;
import quizMaster.quizMaster.dataAccess.abstracts.QuestionDao;
import quizMaster.quizMaster.dataAccess.abstracts.UserQuestionDao;
import quizMaster.quizMaster.entities.concretes.Question;
import quizMaster.quizMaster.entities.concretes.UserQuestion;
import quizMaster.quizMaster.entities.dtos.Request.ChatGPTRequestDto;

@RequiredArgsConstructor
@Service
public class QuestionManager implements QuestionService {

    private final ChatGPTService chatGPTService;
    private final QuestionDao questionDao;
    private final UserQuestionDao userQuestionDao;
    private final UserDao userDao;

    @Override
    public Result createQuestion(String roomId, int questionCount) {
        String userInput = "Türkçe dilinde, HISTORY, POLITICAL, SPORT, POPULAR veya ACTUAL kategorilerinde "+questionCount+ " adet bilgi sorusu üret. " +
                "Her soru 4 şıktan oluşmalı ve şıklar Türkçe olmalıdır. Doğru cevabı belirt ve her sorunun kategorisini ekle." +
                " Sorular şu formatta olmalıdır:"+
                " /// "+
                "--- Türkiye'nin başkenti neresidir? "+
                "--- A) İstanbul "+
                "--- B) Ankara "+
                "--- C) İzmir "+
                "--- D) Ordu "+
                "--- B "+
                "--- POLITICAL ";

        String chatGPT = chatGPTService.getChatResponse(userInput);
        String[] questions = chatGPT.split("///");
        if(questions.length != questionCount) {
            chatGPT = chatGPTService.getChatResponse(userInput);
            questions = chatGPT.split("///");
            save(questions,roomId);
        } else {
            save(questions,roomId);
        }
        return new Result(true, 200);
    }

    @Override
    public Result userCreateQuestion(ChatGPTRequestDto chatGPTRequestDto) {
        String question = "Aşağıdaki soruyu incele ve üç ayrı soru için cevap ver:\n" +
                "\n" +
                "1- Bu bir bilgi sorusu mu? Cevap \"true\" ya da \"false\" olmalıdır.\n" +
                "2- Verilen doğru cevap doğru mu? Cevap \"true\" ya da \"false\" olmalıdır.\n" +
                "3- Doğru cevap nedir? Cevap, sadece doğru şıkkı içermelidir. Örneğin, doğru cevap \"A\" olmalıdır, sadece \"A\" döndür.\n" +
                "Örnek cevap formatı 'True, True, A' şeklinde olmalıdır. Cevaplar arasında virgül ve boşluk olmalıdır.\n"+
                "Soru: \n" +
                chatGPTRequestDto.getQuestion()+"\n"+
                "A) "+chatGPTRequestDto.getOptionA()+"\n"+
                "B) "+chatGPTRequestDto.getOptionB()+"\n"+
                "C) "+chatGPTRequestDto.getOptionC()+"\n"+
                "D) "+chatGPTRequestDto.getOptionD()+"\n"+
                "Cevap: "+chatGPTRequestDto.getAnswer();
        UserQuestion userQuestion = new UserQuestion();
        User user = userDao.getUserById(chatGPTRequestDto.getUserId());
        if(user == null) {
            return  new Result(false,404);
        }
        String chatGptRequest = chatGPTService.getChatResponse(question);
        String[] chatGptResponse = chatGptRequest.split(",");
        if(Boolean.parseBoolean(chatGptResponse[0])){
            userQuestion.setUserID(chatGPTRequestDto.getUserId());
            userQuestion.setQuestion(chatGPTRequestDto.getQuestion().trim());
            userQuestion.setCategory(chatGPTRequestDto.getCategories());
            userQuestion.setOptionsA(chatGPTRequestDto.getOptionA().trim());
            userQuestion.setOptionsB(chatGPTRequestDto.getOptionB().trim());
            userQuestion.setOptionsC(chatGPTRequestDto.getOptionC().trim());
            userQuestion.setOptionsD(chatGPTRequestDto.getOptionD().trim());
            if(Boolean.parseBoolean(chatGptResponse[1])){
                userQuestion.setAnswer(chatGPTRequestDto.getAnswer());
            } else {
                userQuestion.setAnswer(Options.valueOf(chatGptResponse[2].trim().toUpperCase()));
            }
            userQuestionDao.save(userQuestion);
        } else {
            return new Result(false,201,"Lütfen bir bilgi sorusu giriniz.");
        }
        return new Result(true,200);
    }

    private String parseOption(String optionText) {
        return optionText.substring(3).trim();
    }

    private Categories determineCategory(String categoryText) {
        try {
            return Categories.valueOf(categoryText.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Categories.UNDEFINED;
        }
    }

    private Options determineOption(String optionText) {
        try {
            return Options.valueOf(optionText.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Options.UNDEFINED;
        }
    }

    private void save(String[] questions, String roomId) {
        for (String text : questions) {
            String[] split = text.split("---");

            if (split.length < 8) {
                continue;
            }
            Question question = new Question();
            question.setQuestion(split[1].trim());
            question.setOptionsA(parseOption(split[2]));
            question.setOptionsB(parseOption(split[3]));
            question.setOptionsC(parseOption(split[4]));
            question.setOptionsD(parseOption(split[5]));
            question.setAnswer(determineOption(split[6].trim()));
            question.setCategory(determineCategory(split[7].trim()));
            question.setRoomId(roomId);
            questionDao.save(question);
        }
    }
}
