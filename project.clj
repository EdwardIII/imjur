(defproject imjur "0.1.0-SNAPSHOT"
  :description "Share images on the web"
  :url "https://edwardiii.co.uk"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [
                 [org.clojure/clojure "1.10.1"]
                 [compojure "1.6.2"]
                 [http-kit "2.5.3"]
                 [ring/ring-defaults "0.3.3"]
                 [org.clojure/data.json "2.3.1"]
                 [org.clojure/clojurescript "1.10.866"]
                 [cljs-http "0.1.46"]
                 [hipo "0.5.2"]

                 ; TODO: split into dev deps
                 [ring/ring-core "1.9.3"]
                 [ring/ring-devel "1.8.0"]
                 [ring/ring-jetty-adapter "1.9.3"]]

  :main ^:skip-aot imjur.core
  :profiles {:dev 
             {:dependencies [[com.bhauman/figwheel-main "0.2.13"]

                             [com.bhauman/rebel-readline-cljs "0.1.4"] ; for figwheel

                             [cider/piggieback "0.4.2"]
                             [cider/cider-nrepl "0.26.0"]]}

             :repl-options {:nrepl-middleware [cider.piggieback/wrap-cljs-repl]}

             :uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}
             }

  ; TODO: Only load "test" in dev mode
  :source-paths ["src" "test"]
  ;:resource-paths ["target"]
  :plugins [[lein-cljsbuild "1.1.8"]
            [cider/cider-nrepl "0.24.0"]]

  :cljsbuild {
              :builds [ { :id "min" 
                         :source-paths ["src"]
                         :compiler {
                                    :main "imjur-js.core"
                                    :target "resources/public/js"
                                    :asset-path "js/out"
                                    :output-to "resources/public/js/main.min.js"
                                    :output-dir "resources/public/js/out"
                                    :optimizations :advanced}}]}

  ;:figwheel {:ring-handler imjur.core/app
  ;           :css-dirs ["resources/public/css"]
  ;           :nrepl-port 7888
  ;           :nrepl-middleware ["cider.nrepl/cider-middleware"
  ;                              "cemerick.piggieback/wrap-cljs-repl"]
  ;           :repl true}

  :aliases {"fig:build" ["trampoline" "run" "-m" "figwheel.main" "-b" "imjur" "-r"]
            "fig:test"  ["run" "-m" "figwheel.main" "-co" "test.cljs.edn" "-m" "imjur-js.test-runner"]}
  )
