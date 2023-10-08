package squareonex.evetracker.services;

import squareonex.evetrackerdata.model.Blueprint;

public class CopyServiceImpl implements CopyService {
    @Override
    public Float calculateCostPerCopy(Blueprint blueprint) {
        if (!blueprint.getActivity().getName().equals("Copying"))
            throw new IllegalArgumentException(
                    String.format("Blueprint with Activity '%s' found, requires 'Copying'", blueprint.getActivity().getName()));
        Float bpoCost = blueprint.getItemInfo().getAvgCost();
        if (bpoCost == null)
            throw new IllegalArgumentException("Missing the Blueprint Original for copying.");
        return bpoCost / 100;
    }
}
