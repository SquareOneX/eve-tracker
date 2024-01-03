package squareonex.evetracker.converters.text;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import squareonex.evetracker.commands.BlueprintCopyCommand;

import java.util.regex.Pattern;

@Component
public class StringToBlueprintCopyCommand implements Converter<String, BlueprintCopyCommand> {
    @Override
    public BlueprintCopyCommand convert(String source) {
        if (source == null)
            return null;

        //strip the string to only contain the field values
        source = source.substring(source.indexOf("(") + 1, source.lastIndexOf(")")).trim();

        String[] fields = source.split(",");

        return stringToBlueprintCopyCommand(fields);
    }

    private static BlueprintCopyCommand stringToBlueprintCopyCommand(String[] fields) {
        final long id = Long.parseLong(fields[0].split("=")[1].trim());
        final long bpID = Long.parseLong(fields[1].split("=")[1].trim());
        final String bpName = fields[2].split("=")[1].trim();
        final float matMod = Float.parseFloat(fields[3].split("=")[1].trim());
        final float timeMod = Float.parseFloat(fields[4].split("=")[1].trim());
        final int maxRuns = Integer.parseInt(fields[5].split("=")[1].trim());
        final float bpCost = Float.parseFloat(fields[6].split("=")[1].trim());

        final BlueprintCopyCommand target = new BlueprintCopyCommand(id, bpID, bpName);
        target.setMaterialModifier(matMod);
        target.setTimeModifier(timeMod);
        target.setMaxRuns(maxRuns);
        target.setBlueprintCost(bpCost);
        return target;
    }
}
