(ns ethlance.ui.page.new-job
  (:require
   [taoensso.timbre :as log]
   [district.ui.component.page :refer [page]]
   [reagent.core :as r]

   ;; Ethlance Components
   [ethlance.ui.component.button :refer [c-button c-button-icon-label c-button-label]]
   [ethlance.ui.component.circle-button :refer [c-circle-icon-button]]
   [ethlance.ui.component.currency-input :refer [c-currency-input]]
   [ethlance.ui.component.inline-svg :refer [c-inline-svg]]
   [ethlance.ui.component.main-layout :refer [c-main-layout]]
   [ethlance.ui.component.radio-select :refer [c-radio-select c-radio-search-filter-element c-radio-secondary-element]]
   [ethlance.ui.component.rating :refer [c-rating]]
   [ethlance.ui.component.search-input :refer [c-chip-search-input]]
   [ethlance.ui.component.table :refer [c-table]]
   [ethlance.ui.component.tabular-layout :refer [c-tabular-layout]]
   [ethlance.ui.component.tag :refer [c-tag c-tag-label]]
   [ethlance.ui.component.profile-image :refer [c-profile-image]]
   [ethlance.ui.component.carousel :refer [c-carousel c-feedback-slide]]
   [ethlance.ui.component.text-input :refer [c-text-input]]
   [ethlance.ui.component.select-input :refer [c-select-input]]
   [ethlance.ui.component.textarea-input :refer [c-textarea-input]]))


(defn c-arbiter-for-hire
  [{:keys [] :as arbiter}]
  [:div.arbiter-for-hire
   [c-profile-image {}]
   [:div.name "Brian Curran"]
   [c-rating {:rating 3}]
   [:div.price "$10"]
   [c-button
    {:size :small}
    [c-button-label "Invite"]]])


(defmethod page :route.job/new []
  (let [*job-type (r/atom :job)]
    (fn []
      [c-main-layout {:container-opts {:class :new-job-main-container}}
       [:div.forms-left
        [:div.title "New Job"]
        [:div.job-type-input
         [c-radio-select
          {:default-selection :job
           :on-selection (fn [selection] (reset! *job-type selection))
           :flex? true}
          [:job [c-radio-secondary-element "Job"]]
          [:bounty [c-radio-secondary-element "Bounty"]]]]
        [:div.name-input
         [c-text-input {:placeholder "Job Name"}]]
        [:div.category-input
         [c-select-input
          {:label "Select Category"
           :selections ["All Categories" "Programming"]}]]
        [:div.bid-for-radio-input.radio
         [:div.label "Candidates Should Bid For"]
         [c-radio-select
          {:default-selection :hourly-rate
           :on-selection (fn [selection])}
          [:hourly-rate [c-radio-secondary-element "Hourly Rate"]]
          [:fixed-price [c-radio-secondary-element "Fixed Price"]]]]

        [:div.experience-radio-input.radio
         [:div.label "Required Experience Level"]
         [c-radio-select
          {:default-selection :intermediate
           :on-selection (fn [selection])}
          [:beginner [c-radio-secondary-element "Beginner ($)"]]
          [:intermediate [c-radio-secondary-element "Intermediate ($$)"]]
          [:expert [c-radio-secondary-element "Expert ($$$)"]]]]

        [:div.project-length-radio-input.radio
         [:div.label "Estimated Project Length"]
         [c-radio-select
          {:default-selection :day
           :on-selection (fn [selection])}
          [:day [c-radio-secondary-element "Hours or Days"]]
          [:week [c-radio-secondary-element "Weeks"]]
          [:month [c-radio-secondary-element "Months"]]
          [:year [c-radio-secondary-element ">6 Months"]]]]

        [:div.availability-radio-input.radio
         [:div.label "Required Availability"]
         [c-radio-select
          {:default-selection :full-time
           :on-selection (fn [selection])}
          [:full-time [c-radio-secondary-element "Full-Time"]]
          [:part-time [c-radio-secondary-element "Part-Time"]]]]

        [:div.with-arbiter-radio-input.radio
         [:div.label "Arbiter"]
         [c-radio-select
          {:default-selection :with-arbiter
           :on-selection (fn [selection])}
          [:with-arbiter [c-radio-secondary-element "With Arbiter"]]
          [:no-arbiter [c-radio-secondary-element "Without Arbiter"]]]]]

       [:div.forms-right
        [:div.required-skills-chip.chip
         [:div.label "Required Skills"]
         [c-chip-search-input
          {:search-icon? false
           :placeholder "Search Skills"
           :allow-custom-chips? true}]]

        [:div.description-text.chip
         [:div.label "Description"]
         [c-textarea-input
          {:placeholder "Enter Description"}]]]

       [:div.arbiters
        [c-arbiter-for-hire {}]
        [c-arbiter-for-hire {}]
        [c-arbiter-for-hire {}]]

       [:div.button
        {:on-click (fn [e])}
        [:div.label "Create"]]])))
