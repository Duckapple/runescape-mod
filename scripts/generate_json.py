import os
import shutil
import re

runes = set()
r = re.compile(r'\s+[A-Z]+\("(.+?)"\)')
with open("src/main/java/dev/simongreen/runescape/common/RuneType.java", "r") as f:
    for line in f.readlines():
        x = re.match(r, line)
        x = x.group(1) if x else None
        if x:
            runes.add(x)


def expand_recurse(path: str):
    d = os.listdir(path)
    for f in d:
        resolved = path + "/" + f
        if f.endswith(".base.json"):
            baseF = resolved.replace(".base.", ".")
            shutil.copyfile(resolved, baseF)
            for rune in runes:
                if rune in f:
                    contents = ""
                    with open(resolved) as file:
                        contents = file.read()
                    rest = runes - {rune}
                    for otherRune in rest:
                        newContents = contents.replace(rune, otherRune)
                        with open(baseF.replace(rune, otherRune), "w") as wf:
                            wf.write(newContents)
        elif os.path.isdir(resolved):
            expand_recurse(resolved)


expand_recurse("src/main/resources/assets")
