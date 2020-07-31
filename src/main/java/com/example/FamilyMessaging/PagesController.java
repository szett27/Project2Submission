package com.example.FamilyMessaging;


import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PagesController {





        private final MessageRepository messagerepo;
        private final UserRepository userrepo;
        private final FixitRepository fixitrepo;
        private final BuyitRepository buyitrepo;
        private final BirthdayRepository bdayrepo;
        private final ChristmasRepository xmasrepo;


        public PagesController(MessageRepository messagerepo,
                               UserRepository userrepo,
                               FixitRepository fixitrepo,
                               BuyitRepository buyitrepo,
                               ChristmasRepository xmasrepo,
                               BirthdayRepository bdayrepo) {
            this.messagerepo = messagerepo;
            this.userrepo = userrepo;
            this.fixitrepo = fixitrepo;
            this.buyitrepo = buyitrepo;
            this.xmasrepo = xmasrepo;
            this.bdayrepo = bdayrepo;
        }

        //Admin can see all users and all messages
        @GetMapping("/allUsers")
        public Iterable<User> allUsers() {
            return this.userrepo.findAll();
        }

        @GetMapping("/allFixes")
        public Iterable<Fixit> allFixes() {
            return this.fixitrepo.findAll();
        }

        @GetMapping("/allBuys")
        public Iterable<Buyit> allBuys() {
            return this.buyitrepo.findAll();
        }

        @GetMapping("/ChristmasWishlist")
        public Iterable<Christmas> allXmas() {
            return this.xmasrepo.findAll();
        }

        @GetMapping("/BirthdayWishlist")
        public Iterable<Birthday> Bdays() {
            return this.bdayrepo.findAll();
        }

        @PostMapping("/newUser")
        public User createUser(@RequestBody User user) {
            return this.userrepo.save(user);
        }

        @GetMapping("/allMessages")
        public Iterable<Message> allMessages() {
            return this.messagerepo.findAll();
        }

        @PostMapping("/newMessage")
        public void create(@RequestBody Message message) {
             String[] splitMessage = message.getContent().split(" ");
             for(String word: splitMessage) {
                 word = word.toLowerCase();
                 System.out.println(word);
                 //fix, repair, etc
                 if (word.equals("fix") || word.equals("repair")) {
                     Fixit fixit = new Fixit();
                     fixit.setContent(message.getContent());
                     fixit.setStatus("Pending");
                     this.fixitrepo.save(fixit);

                 } else if (word.equals("buy") ||
                         word.equals("order") ||
                         word.equals("purchase") ||
                         word.equals("get")) {
                     Buyit buyit = new Buyit();
                     buyit.setContent(message.getContent());
                     buyit.setStatus("Pending");
                     this.buyitrepo.save(buyit);
                 } else if (word.equals("christmas") ||
                            word.equals("santa")){

                     Christmas christmas = new Christmas();
                     christmas.setContent(message.getContent());
                     christmas.setStatus("Pending");
                     this.xmasrepo.save(christmas);
                 } else if (word.equals("birthday")){
                     Birthday birthday = new Birthday();
                     birthday.setContent(message.getContent());
                     this.bdayrepo.save(birthday);
                 }
                 //order, buy, etc
                 //remove - stretch
                 else {
                    this.messagerepo.save(message);
                 }
             }
        }



        //update methods

    @PatchMapping("/fixit/{id}")
    public Fixit patch(@PathVariable Long id, @RequestBody Fixit updates) {
        Optional<Fixit> current = this.fixitrepo.findById(id);
        if (updates.getContent() != null) {
            current.get().setContent(updates.getContent());
        }
        if (updates.getStatus() != null) {
            current.get().setStatus(updates.getStatus());
        }

        if (updates.getSender_id() != null){
            current.get().setSender_id(updates.getSender_id());
        }

        return this.fixitrepo.save(current.get());
    }

    @PatchMapping("/buyit/{id}")
    public Buyit patch(@PathVariable Long id, @RequestBody Buyit updates) {
        Optional<Buyit> current = this.buyitrepo.findById(id);
        if (updates.getContent() != null) {
            current.get().setContent(updates.getContent());
        }
        if (updates.getStatus() != null) {
            current.get().setStatus(updates.getStatus());
        }

        if (updates.getSender_id() != null){
            current.get().setSender_id(updates.getSender_id());
        }

        return this.buyitrepo.save(current.get());
    }

    @PatchMapping("/christmas/{id}")
    public Christmas patch(@PathVariable Long id, @RequestBody Christmas updates) {
        Optional<Christmas> current = this.xmasrepo.findById(id);
        if (updates.getContent() != null) {
            current.get().setContent(updates.getContent());
        }
        if (updates.getStatus() != null) {
            current.get().setStatus(updates.getStatus());
        }

        if (updates.getSender_id() != null){
            current.get().setSender_id(updates.getSender_id());
        }

        return this.xmasrepo.save(current.get());
    }

    @PatchMapping("/birthday/{id}")
    public Birthday patch(@PathVariable Long id, @RequestBody Birthday updates) {
        Optional<Birthday> current = this.bdayrepo.findById(id);
        if (updates.getContent() != null) {
            current.get().setContent(updates.getContent());
        }
        if (updates.getStatus() != null) {
            current.get().setStatus(updates.getStatus());
        }

        if (updates.getSender_id() != null){
            current.get().setSender_id(updates.getSender_id());
        }

        return this.bdayrepo.save(current.get());
    }



}
