# 🔐 Regex

Some systems use the Keys for identification and their structure, to quickly adapt to this behavior, Stacked introduces a global variable.

This global variable, called **KEY\_REGEX**, is contained inside the Keying.kt file, and is just the Regex, which defines the possibilities of content inside a Key's Namespace and value!

Here is the **KEY\_REGEX** value in its current state:

<pre class="language-kotlin" data-title="Keying.kt"><code class="lang-kotlin"><strong>val KEY_REGEX = "[a-z0-9_.-]".toRegex()</strong></code></pre>
